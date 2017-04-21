package arrol.com.xiaomi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import arrol.com.xiaomi.presenter.UserLoginPresenter;
import arrol.com.xiaomi.view.IUserLoginView;
import arrol.com.xiaomi.view.activity.MainActivity;
import arrol.com.xiaomi.view.activity.RegisterActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class LoginActivity extends AppCompatActivity implements IUserLoginView{

    @Bind(R.id.password_login) EditText password;
    @Bind(R.id.username_login) EditText userName;
    @Bind(R.id.button_login) Button login;
    @Bind(R.id.bt_register_loginPg) Button btRegister;
    @Bind(R.id.progress_login) ProgressBar progressBar;

    private UserLoginPresenter presenter=new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clear();
            }
        });
    }
    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void toRegisterActivity() {
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
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
    public void toMainActivity(BmobUser user) {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError(String s) {
        Toast.makeText(this,"登录失败："+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}
