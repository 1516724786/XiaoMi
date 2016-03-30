package arrol.com.xiaomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadPoolExecutor;

import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.presenter.UserLoginPresenter;
import arrol.com.xiaomi.view.IUserLoginView;
import arrol.com.xiaomi.view.activity.RegisterActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IUserLoginView{

    @Bind(R.id.password_login) EditText password;
    @Bind(R.id.username_login) EditText userName;
    @Bind(R.id.button_login) Button login;
    @Bind(R.id.register_view_login) TextView register;
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
        register.setOnClickListener(new View.OnClickListener() {
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
    public void toMainActivity(User user) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"onError",Toast.LENGTH_SHORT).show();
    }

}
