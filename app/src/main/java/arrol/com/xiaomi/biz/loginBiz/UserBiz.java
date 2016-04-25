package arrol.com.xiaomi.biz.loginBiz;

import android.content.Context;

import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.biz.loginBiz.IUserBiz;
import arrol.com.xiaomi.biz.loginBiz.OnLoginListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by User on 2016/3/26.
 */
public class UserBiz implements IUserBiz {
    @Override
    public void login(final Context context,final String username, final String password, final OnLoginListener loginListener) {
        new Thread(){
            @Override
            public void run(){
                final BmobUser user=new BmobUser();
                user.setUsername(username);
                user.setPassword(password);
                user.login(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        loginListener.loginSuccess(user);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        loginListener.loginFailed(s);
                    }
                });

            }
        }.start();
    }
}
