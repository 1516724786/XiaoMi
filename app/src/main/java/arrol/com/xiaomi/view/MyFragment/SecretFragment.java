package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.adapter.AdapterGridView;
import arrol.com.xiaomi.view.activity.sq_helper.PsRecordActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 */
public class SecretFragment extends Fragment{

    @Bind(R.id.grid_fragment_secret)
    GridView gridView;
    @Bind(R.id.text1_fragment_secret)
    TextView textView1;
    @Bind(R.id.text2_fragment_secret)
    TextView textView2;
    @Bind(R.id.text3_fragment_secret)
    TextView textView3;
    @Bind(R.id.text4_fragment_secret)
    TextView textView4;

    private View view;

    private List<String> mData;
    private AdapterGridView adapterGridView;
    private StringBuffer buffer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.fragment_secret,container,false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mData=new ArrayList<>();
        initData();

        adapterGridView=new AdapterGridView(getActivity(),mData);
        gridView.setAdapter(adapterGridView);

        buffer=new StringBuffer("");
        initEvent(gridView);

    }
    protected void initData(){
        for (int i=1; i<13; i++){
            if(i<10){
               mData.add(""+i);
            }
            else if(i == 10){

                mData.add("");
            }
            else if(i == 11){
                mData.add("0");
            }
            else{
                mData.add("");
            }
        }
    }
    protected void initEvent(GridView gridView){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(buffer.length() < 4){
                    if(position < 9){
                        buffer.append(""+(position+1));
                    }
                    else if(position == 10){
                        buffer.append("0");
                    }
                    else ;
                }
                else ;

                if(position == 11){
                    if(buffer.length() != 0){
                        buffer.deleteCharAt(buffer.length()-1);
                    }
                }

                if(buffer.length() == 1){
                    textView1.setText("*");
                    textView2.setText("");
                    textView3.setText("");
                    textView4.setText("");
                }
                else if(buffer.length() == 2){
                    textView2.setText("*");
                    textView3.setText("");
                    textView4.setText("");
                }
                else if(buffer.length() == 3){
                    textView3.setText("*");
                    textView4.setText("");
                }
                else if(buffer.length() == 4){
                    textView4.setText("*");
                }
                else {
                    textView1.setText("");
                    textView2.setText("");
                    textView3.setText("");
                    textView4.setText("");
                }
                if (buffer.length() == 4 && buffer.toString().equals("1234")){
                    textView1.setText("");
                    textView2.setText("");
                    textView3.setText("");
                    textView4.setText("");
                    buffer.delete(0,4);
                    toPsRecordPage();
                }
            }
        });
    }
    private void toPsRecordPage(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), PsRecordActivity.class);
        startActivity(intent);
    }
}
