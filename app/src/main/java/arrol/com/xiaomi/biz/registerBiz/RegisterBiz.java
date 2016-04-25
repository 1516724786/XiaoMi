package arrol.com.xiaomi.biz.registerBiz;

import android.content.Context;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by User on 2016/3/29.
 */
public class RegisterBiz implements IRegisterBiz{
    @Override
    public void register(final Context context,final String userName, final String password, final String password_again, final OnRegisterListener registerListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(password.equals(password_again) && !password.equals("") && !userName.equals("")){
                    final BmobUser user=new BmobUser();
                    user.setUsername(userName);
                    user.setPassword(password);
                    user.signUp(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            registerListener.registerSuccess(user);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            registerListener.registerFailed(s);
                        }
                    });
                }
                else if(!password.equals(password_again)){
                    registerListener.registerFailed("两次密码不等！");
                }
                else{
                    registerListener.registerFailed("用户名或密码为空！");
                }
            }
        }).start();
    }
}
