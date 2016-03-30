package arrol.com.xiaomi.biz.registerBiz;

import arrol.com.xiaomi.bean.User;

/**
 * Created by User on 2016/3/29.
 */
public class RegisterBiz implements IRegisterBiz{
    @Override
    public void register(final String userName, final String password, final String password_again, final OnRegisterListener registerListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(password.equals(password_again) && !password.equals("")){
                    User user=new User();
                    user.setUserName(userName);
                    user.setPassWord(password);
                    registerListener.registerSuccess(user);
                }
                else {
                    registerListener.registerFailed();
                }
            }
        }).start();
    }
}
