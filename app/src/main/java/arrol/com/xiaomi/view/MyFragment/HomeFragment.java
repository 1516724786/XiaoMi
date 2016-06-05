package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.adapter.AdapterRecordIn;
import arrol.com.xiaomi.adapter.AdapterRecordOut;
import arrol.com.xiaomi.adapter.MyPagerAdapter;
import arrol.com.xiaomi.customView.MyListView;
import arrol.com.xiaomi.presenter.HomeFragmentPresenter;
import arrol.com.xiaomi.view.IHomeFragmentView;
import arrol.com.xiaomi.view.activity.AddRecordActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 *
 */
public class HomeFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener,
        MyListView.IReFlashListener,IHomeFragmentView{

    @Bind(R.id.tabLayout_fragment_home)
    TabLayout tabLayout;
    @Bind(R.id.viewPager_fragment_main)
    ViewPager viewPager;
    @Bind(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    private List<String> listTitle;
    private SwipeRefreshLayout refreshLayout_out;
    private SwipeRefreshLayout refreshLayout_in;
    private MyListView listView_in;
    private MyListView listView_out;
    private List<Map<String,Object>> dataOut;
    private AdapterRecordOut adapterRecordOut;
    private List<Map<String,Object>> dataIn;
    private AdapterRecordIn adapterRecordIn;
    private HomeFragmentPresenter presenter;

    private int pageNumberOut;
    private int pageNumberIn;
    private int stateOut;
    private int stateIn;
    private int distiguish;
    private final int CACHE=1;//表示先从缓存里面获取数据
    private final int NETWORK=2;//表示先从网络获取数据

    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataOut = new ArrayList<>();
        dataIn = new ArrayList<>();
        presenter=new HomeFragmentPresenter(this);
        handler=new Handler();
        stateOut =CACHE;
        stateIn=CACHE;
        pageNumberOut = 0;
        pageNumberIn = 0;
        initView();

        listView_out.setInterface(this);
        adapterRecordOut = new AdapterRecordOut(getActivity(), dataOut);
        listView_out.setAdapter(adapterRecordOut);
        presenter.LoadingDataOut(pageNumberOut, stateOut);

        listView_in.setInterface(this);
        adapterRecordIn = new AdapterRecordIn(getActivity(),dataIn);
        listView_in.setAdapter(adapterRecordIn);
//        presenter.LoadingDataIn(pageNumberIn, stateIn);

        initEvent();

    }
    private void initView(){
        /**
         * 将ViewPager与TableLayout结合
         */
        listTitle=new ArrayList<>();
        List<View> listPager = new ArrayList<>();

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View view1 = mInflater.inflate(R.layout.record_out_show, null);
        View view2= mInflater.inflate(R.layout.record_in_show, null);
        listPager.add(view1);
        listPager.add(view2);
        listTitle.add("hello");
        listTitle.add("world");

        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)));

        MyPagerAdapter mAdapter = new MyPagerAdapter(listPager,listTitle);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mAdapter);

        /**
         * 找View1里面的控件
         */
        refreshLayout_out=(SwipeRefreshLayout)view1.findViewById(R.id.record_out_refresh);
        refreshLayout_out.setColorSchemeResources(android.R.color.holo_blue_bright);
        refreshLayout_out.setOnRefreshListener(this);
        listView_out=(MyListView)view1.findViewById(R.id.record_out_myListView);
        /**
         * 找出view2中的控件
         */
        refreshLayout_in=(SwipeRefreshLayout)view2.findViewById(R.id.record_in_refresh);
        refreshLayout_in.setColorSchemeResources(android.R.color.holo_blue_bright);
        refreshLayout_in.setOnRefreshListener(this);
        listView_in=(MyListView)view2.findViewById(R.id.record_in_myListView);

    }
    private void initEvent(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddRecordActivity.class);
                startActivity(intent);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1){
                    distiguish=1;
                    presenter.LoadingDataIn(pageNumberIn, stateIn,distiguish);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRefresh() {
        stateOut =NETWORK;
        stateIn = NETWORK;
        pageNumberOut = 0;//刷新从头再开始！
        pageNumberIn = 0;
        if(viewPager.getCurrentItem() == 0){
            presenter.RefreshDataOut(pageNumberOut);
        }
        else {
            presenter.RefreshDataIn(pageNumberIn);
        }

    }

    @Override
    public void onReFlash() {
        if (viewPager.getCurrentItem() == 0){
            pageNumberOut++;
            presenter.LoadingDataOut(pageNumberOut, stateOut);
        }
        else {
            pageNumberIn++;
            if (distiguish == 1){
                distiguish = 0;
            }
            presenter.LoadingDataIn(pageNumberIn, stateIn,distiguish);
        }

    }

    @Override
    public Context getContext(){
        return getActivity();
    }

    @Override
    public void showFailedError(String s) {
        Toast.makeText(getActivity(),"Error:"+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Map<String, Object>> getListOut() {
        return dataOut;
    }

    @Override
    public List<Map<String, Object>> getListIn() {
        return dataIn;
    }

    @Override
    public void changedDataOut() {
        adapterRecordOut.notifyDataSetChanged();
        listView_out.refreshComplete();
        refreshLayout_out.setRefreshing(false);
    }

    @Override
    public void changedDataIn() {
        adapterRecordIn.notifyDataSetChanged();
        listView_in.refreshComplete();
        refreshLayout_in.setRefreshing(false);
    }
}
