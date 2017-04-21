package arrol.com.xiaomi.view.activity.sq_helper;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import arrol.com.xiaomi.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PsRecordActivity extends AppCompatActivity {
    @Bind(R.id.Sq_list)ListView Sq_listView;
    @Bind(R.id.bt_sq_float)FloatingActionButton aButton;
    @Bind(R.id.toolbar)Toolbar toolbar;

    private MyDatabaseHelper dbHelper;
    private SimpleAdapter adapter;
    ArrayList<Map<String,Object>> data=new ArrayList<Map<String,Object>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps_record);
        ButterKnife.bind(this);

        toolbar.setTitle("密码本");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dbHelper=new MyDatabaseHelper(this,"myDict.db3",1);
        adapter=new SimpleAdapter(this,data,R.layout.sq_item,
                new String[]{"mark","user","pass","freeNumber"},
                new int[]{R.id.mark,R.id.sq_user,R.id.sq_pass,R.id.freeNumber});
        Sq_query();
        Sq_listView.setAdapter(adapter);
        MyItemOnClick(Sq_listView);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(PsRecordActivity.this, Sq_AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void MyItemOnClick(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView1=(ListView)parent;
                HashMap<String,String> map=
                        (HashMap<String,String>)listView1.getItemAtPosition(position);
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",map);
                intent.putExtras(bundle);
                intent.setClass(PsRecordActivity.this, Sq_update.class);
                startActivity(intent);
            }
        });
    }

    private void Sq_query(){
        if(data!=null){
            data.clear();
        }

        String key="";
        Cursor cursor=dbHelper.getReadableDatabase().rawQuery("select * from dict where title like?",
                new String[]{"%"+key+"%"});

        while(cursor.moveToNext()){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("mark",cursor.getString(1));
            map.put("user",cursor.getString(2));
            map.put("pass", cursor.getString(3));
            map.put("freeNumber",cursor.getString(4));
            data.add(map);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Sq_query();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroy()
    {
        //程序退出是关闭SQLiteDatabase
        super.onDestroy();
        if(dbHelper!=null)
        {
            dbHelper.close();
        }
    }
}
