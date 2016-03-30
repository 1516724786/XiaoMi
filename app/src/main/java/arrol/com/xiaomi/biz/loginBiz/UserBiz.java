package arrol.com.xiaomi.biz.loginBiz;

import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.biz.loginBiz.IUserBiz;
import arrol.com.xiaomi.biz.loginBiz.OnLoginListener;

/**
 * Created by User on 2016/3/26.
 */
public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if("hxb".equals(username) && "123456".equals(password)){
                    User user=new User();
                    user.setUserName(username);
                    user.setPassWord(password);
                    loginListener.loginSuccess(user);
                }
                else{
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
