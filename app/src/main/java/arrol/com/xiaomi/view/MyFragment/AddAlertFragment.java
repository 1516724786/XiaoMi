package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.adapter.AdapterRecycler;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 */
public class AddAlertFragment extends Fragment{

    @Bind(R.id.fragment_add_alert_recyclerView)RecyclerView recyclerView;

    private AdapterRecycler adapterRecycler;
    private List<String> mData;
    private View view;

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
        initData();
        adapterRecycler=new AdapterRecycler(getActivity(),mData);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initEvent();
    }

    private void initData(){
        mData=new ArrayList<>();
        for(int i=1;i<5;i++){
            mData.add(""+i);
        }
    }

    private void initEvent() {
        adapterRecycler.setItemClickListener(new AdapterRecycler.OnItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (position == mData.size() - 1) {
                    adapterRecycler.addData(position);
                } else {
                    Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapterRecycler.setItemLongClickListener(new AdapterRecycler.OnItemLongListener() {
            @Override
            public void onItemLongClickListener(View view, final int position) {
                if(position != mData.size()-1){
                    Snackbar.make(view,"是否删除~~",Snackbar.LENGTH_LONG).setAction("删除", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapterRecycler.removeData(position);
                        }
                    }).setActionTextColor(Color.parseColor("#f42f2f")).show();
                }
            }
        });
    }
}
