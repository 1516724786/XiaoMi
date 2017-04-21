package arrol.com.xiaomi.view.activity.alert;

import android.app.Activity;
import android.app.Service;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import arrol.com.xiaomi.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AlarmActivity extends Activity {

    @Bind(R.id.bt_alarm_sure)
    Button sure;
    @Bind(R.id.tv_alarm_content)
    TextView tvContent;

    private Vibrator vibrator;
    private AlertDataBaseHelper alertHelper;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{400,600,800,1200},0);
        alertHelper = new AlertDataBaseHelper(this, "alert.db3",1);

        String str = getIntent().getStringExtra("msg");
        Log.i("msg",str);

        Cursor cursor=alertHelper.getReadableDatabase().rawQuery("select * from alert where number like?",
                new String[]{"%"+str+"%"});
        while (cursor.moveToNext()){
            content = cursor.getString(2);
        }
        tvContent.setText("”"+content+"”"+" 的时间到了！");
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
                AlarmActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
    }
}
