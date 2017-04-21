package arrol.com.xiaomi.view.activity.sq_helper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arrol.com.xiaomi.R;


public class Sq_AddActivity extends Activity {

    private MyDatabaseHelper helper;
    private EditText mark;
    private EditText pass;
    private EditText user;
    private Button sure;
    private SharedPreferences freeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sq_addactivity);
        helper=new MyDatabaseHelper(this,"myDict.db3",1);

        mark=(EditText)findViewById(R.id.add_mark);
        pass=(EditText)findViewById(R.id.add_pass);
        user=(EditText)findViewById(R.id.add_user);
        sure=(Button)findViewById(R.id.add_sure);

        freeNumber=getSharedPreferences("freeNumber",MODE_PRIVATE);
        final SharedPreferences.Editor editor=freeNumber.edit();
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                i=freeNumber.getInt("freeNumber", 0);
                i=i+1;
                helper.getReadableDatabase().execSQL("insert into dict values(null,?,?,?,?)",
                        new String[]{mark.getText().toString(), user.getText().toString(),
                                pass.getText().toString(), i + ""});
                Toast.makeText(getApplicationContext(),"Add Success", Toast.LENGTH_SHORT).show();
                editor.putInt("freeNumber",i);
                editor.commit();
                finish();
            }
        });
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(helper!=null)
        {
            helper.close();
        }
    }
}
