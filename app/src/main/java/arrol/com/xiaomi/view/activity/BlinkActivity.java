package arrol.com.xiaomi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arrol.com.xiaomi.LoginActivity;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/4/27.
 */
public class BlinkActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "0b8e9e334abe15d16b8865c13b77336f");
        BmobUser user=BmobUser.getCurrentUser(this,BmobUser.class);
        if(user == null){
            Intent intent=new Intent();
            intent.setClass(BlinkActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent=new Intent();
            intent.setClass(BlinkActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
