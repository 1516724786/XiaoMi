package arrol.com.xiaomi;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by User on 2016/4/25.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Bmob.initialize(this,"0b8e9e334abe15d16b8865c13b77336f");
    }

}
