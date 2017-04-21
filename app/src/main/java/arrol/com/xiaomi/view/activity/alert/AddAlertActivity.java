package arrol.com.xiaomi.view.activity.alert;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import arrol.com.xiaomi.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AddAlertActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bt_alert_date)
    Button btDate;
    @Bind(R.id.tv_alert_date)
    TextView tvDate;
    @Bind(R.id.bt_alert_time)
    Button btTime;
    @Bind(R.id.tv_alert_time)
    TextView tvTime;
    @Bind(R.id.et_alert_remark)
    EditText etRemark;
    @Bind(R.id.bt_alert_sure)
    Button btSure;

    private AlertDataBaseHelper alertHelper;
    private SharedPreferences alertNumber;
    private SharedPreferences.Editor editor;
    private Calendar alarmCalendar;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);
        ButterKnife.bind(this);
        alarmCalendar = Calendar.getInstance();
        alarmManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);

        initToolbar();
        initView();

        alertHelper = new AlertDataBaseHelper(this, "alert.db3",1);
        alertNumber = getSharedPreferences("alertNumber",MODE_PRIVATE);
        editor = alertNumber.edit();

    }

    private void initToolbar() {
        toolbar.setTitle("新建记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        final Calendar calendar = Calendar.getInstance();

        btTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddAlertActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime.setText(String.format("%d:%d",hourOfDay,minute));
                        alarmCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        alarmCalendar.set(Calendar.MINUTE,minute);
                    }
                },0,0,true).show();
            }
        });

        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddAlertActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        alarmCalendar.set(Calendar.YEAR,year);
                        alarmCalendar.set(Calendar.MONTH,monthOfYear);
                        alarmCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        tvDate.setText(String.format("%d.%d.%d",year,monthOfYear+1,dayOfMonth));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                i=alertNumber.getInt("alertNumber", 0);
                i=i+1;
                alertHelper.getReadableDatabase().execSQL("insert into alert values(null,?,?,?)",
                        new String[]{tvDate.getText().toString()+" "+tvTime.getText().toString(),
                                etRemark.getText().toString(), String.valueOf(i)});
                editor.putInt("alertNumber",i);
                editor.commit();

                Intent intent = new Intent(AddAlertActivity.this,AlarmActivity.class);
                intent.putExtra("msg",String.valueOf(i));
                PendingIntent pi = PendingIntent.getActivity(AddAlertActivity.this,i,intent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmCalendar.getTimeInMillis(),pi);

                finish();
            }
        });

        tvDate.setText(calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH)+1)+"."
                +calendar.get(Calendar.DATE));
        tvTime.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"
                +calendar.get(Calendar.MINUTE));
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(alertHelper!=null)
        {
            alertHelper.close();
        }
    }
}

