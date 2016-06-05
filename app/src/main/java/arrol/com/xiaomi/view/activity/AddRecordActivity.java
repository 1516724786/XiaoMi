package arrol.com.xiaomi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.bean.MonthTotalOut;
import arrol.com.xiaomi.bean.Record;
import arrol.com.xiaomi.presenter.AddRecordPresenter;
import arrol.com.xiaomi.view.IAddRecordView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

public class AddRecordActivity extends AppCompatActivity implements IAddRecordView {

    @Bind(R.id.radioGroup_kind_record)
    RadioGroup radioGroup_kind;
    @Bind(R.id.time_record)
    EditText time_record;
    @Bind(R.id.amount_record)
    EditText amount_record;
    @Bind(R.id.radioGroup_category_record)
    RadioGroup radioGroup_category;
    @Bind(R.id.remark_record)
    EditText remark_record;
    @Bind(R.id.category_record)
    LinearLayout linearLayout;
    @Bind(R.id.progress_record)
    ProgressBar progressBar;

    @Bind(R.id.sure_record)
    Button sure;

    private AddRecordPresenter presenter =new AddRecordPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ButterKnife.bind(this);
        initView();
        initEvent();

    }
    private void initView(){
        radioGroup_kind.check(R.id.out_record);
        radioGroup_category.check(R.id.food_record);

        Calendar calendar=Calendar.getInstance();
        time_record.setText(calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"
        +calendar.get(Calendar.DATE)+"日"+calendar.get(Calendar.HOUR_OF_DAY)+"时"
        +calendar.get(Calendar.MINUTE)+"分");
    }
    private void initEvent(){

        radioGroup_kind.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.out_record) {
                            if (linearLayout.getVisibility() == View.GONE) {
                                linearLayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            linearLayout.setVisibility(View.GONE);
                        }
                    }
                });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressBar.getVisibility() == View.GONE){
                    presenter.addRecord();
                }
            }
        });
    }

    private Record getData(){
        final Record record = new Record();
        switch(radioGroup_kind.getCheckedRadioButtonId()){
            case R.id.out_record:
                record.setKind("支出");
                break;
            case R.id.in_record:
                record.setKind("收入");
                break;
        }

        if(linearLayout.getVisibility() == View.VISIBLE){
            switch(radioGroup_category.getCheckedRadioButtonId())
            {
                case R.id.food_record:
                    record.setCategory("饮食");
                    break;
                case R.id.cloth_record:
                    record.setCategory("服装");
                    break;
                case R.id.rent_record:
                    record.setCategory("房租");
                    break;
                case R.id.trip_record:
                    record.setCategory("旅途");
                    break;
                case R.id.other_record:
                    record.setCategory("其它");
                    break;
            }
        }

        if(!amount_record.getText().toString().equals("")){
            record.setMoney(Float.parseFloat(amount_record.getText().toString()));
        }
        record.setTime(time_record.getText().toString());
        record.setRemark(remark_record.getText().toString());
        record.setUser(BmobUser.getCurrentUser(this,BmobUser.class));
        return record;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Record getRecord() {
        return getData();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity() {
        Intent intent=new Intent();
        intent.setClass(AddRecordActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError(String s) {

        Toast.makeText(this,"提交失败："+s,Toast.LENGTH_SHORT).show();
    }
}
