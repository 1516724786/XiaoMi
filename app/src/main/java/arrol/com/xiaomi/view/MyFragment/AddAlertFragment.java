package arrol.com.xiaomi.view.MyFragment;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.adapter.AdapterRecycler;
import arrol.com.xiaomi.view.activity.alert.AddAlertActivity;
import arrol.com.xiaomi.view.activity.alert.AlarmActivity;
import arrol.com.xiaomi.view.activity.alert.AlertDataBaseHelper;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 */
public class AddAlertFragment extends Fragment{

    @Bind(R.id.fragment_add_alert_recyclerView)RecyclerView recyclerView;

    private AdapterRecycler adapterRecycler;
    private View view;

    private AlertDataBaseHelper alertHelper;
    private ArrayList<Map<String,String>> alertData = new ArrayList<>();

    private AlarmManager am;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        if(view == null){
            view=inflater.inflate(R.layout.fragment_add_alert, container, false);
        }
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        alertHelper = new AlertDataBaseHelper(getActivity(),"alert.db3",1);
        am = (AlarmManager)getActivity().getSystemService(Service.ALARM_SERVICE);
        initData();
        adapterRecycler=new AdapterRecycler(getActivity(),alertData);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initEvent();
    }

    private void initData(){
        if(alertData!=null){
            alertData.clear();
        }

        String key="";
        Cursor cursor=alertHelper.getReadableDatabase().rawQuery("select * from alert where time like?",
                new String[]{"%"+key+"%"});

        while(cursor.moveToNext()){
            Map<String,String> map=new HashMap<String,String>();
            map.put("time",cursor.getString(1));
            map.put("remark",cursor.getString(2));
            map.put("number", cursor.getString(3));
            alertData.add(map);
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("time","");
        map.put("remark","");
        map.put("number","");
        alertData.add(map);
    }

    private void initEvent() {
        adapterRecycler.setItemClickListener(new AdapterRecycler.OnItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (position == alertData.size() - 1) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), AddAlertActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapterRecycler.setItemLongClickListener(new AdapterRecycler.OnItemLongListener() {
            @Override
            public void onItemLongClickListener(View view, final int position) {
                if(position != alertData.size()-1){
                    Snackbar.make(view,"是否删除~~",Snackbar.LENGTH_LONG).setAction("删除", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*
                            * 这里还得写取消闹钟的代码
                            * */
                            Intent intent = new Intent(getActivity(),AlarmActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getActivity(),Integer.parseInt(alertData.get(position).get("number")),intent,0);
                            am.cancel(pi);

                            alertHelper.getReadableDatabase().delete
                                    ("alert", "number=?", new String[]{alertData.get(position).get("number")});
                            adapterRecycler.removeData(position);
                        }
                    }).setActionTextColor(Color.parseColor("#f42f2f")).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        adapterRecycler.notifyDataSetChanged();
    }
}
