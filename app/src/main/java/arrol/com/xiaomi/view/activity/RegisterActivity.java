package arrol.com.xiaomi.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import arrol.com.xiaomi.LoginActivity;
import arrol.com.xiaomi.R;
import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.presenter.RegisterPresenter;
import arrol.com.xiaomi.view.IRegisterView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView{


    @Bind(R.id.username_register)EditText userName;
    @Bind(R.id.password_register)EditText password;
    @Bind(R.id.password_again_register)EditText passwordAgain;
    @Bind(R.id.button_register)Button button_register;
    @Bind(R.id.back_register)Button button_back;
    @Bind(R.id.progress_register)ProgressBar progressBar_register;

    RegisterPresenter registerPresenter=new RegisterPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.register();
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginActivity();
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
    public String getPasswordAgain() {
        return passwordAgain.getText().toString();
    }

    @Override
    public void showLoading() {
        progressBar_register.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar_register.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toLoginActivity() {
        Intent intent=new Intent();
        intent.setClass(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"onError",Toast.LENGTH_SHORT).show();
    }
}
