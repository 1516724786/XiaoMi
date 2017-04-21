package arrol.com.xiaomi.view.activity.sq_helper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;

import arrol.com.xiaomi.R;

public class Sq_update extends Activity {

    private EditText mark;
    private EditText user;
    private EditText pass;
    private Button delete;
    private Button update;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_update);
        mark=(EditText)findViewById(R.id.update_mark);
        user=(EditText)findViewById(R.id.update_user);
        pass=(EditText)findViewById(R.id.update_pass);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);

        Intent intent=getIntent();
        Bundle data=intent.getExtras();
        final HashMap<String,String> map= (HashMap<String,String>)data.getSerializable("data");

        mark.setText(map.get("mark"));
        user.setText(map.get("user"));
        pass.setText(map.get("pass"));
        dbHelper=new MyDatabaseHelper(this,"myDict.db3",1);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getReadableDatabase().delete
                        ("dict", "number=?", new String[]{map.get("freeNumber")});
                Toast.makeText(getApplicationContext(),"Delete Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=user.getText().toString();
                String passWord=pass.getText().toString();
                String title=mark.getText().toString();
                ContentValues values=new ContentValues();
                values.put("word",userName);
                values.put("detail", passWord);
                values.put("title",title);
                dbHelper.getReadableDatabase().update("dict", values, "number=?",
                        new String[]{map.get("freeNumber")});
                Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(dbHelper!=null)
        {
            dbHelper.close();
        }
    }
}
