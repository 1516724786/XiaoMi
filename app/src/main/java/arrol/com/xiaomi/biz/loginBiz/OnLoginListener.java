package arrol.com.xiaomi.biz.loginBiz;

import arrol.com.xiaomi.bean.User;

/**
 * Created by User on 2016/3/26.
 */
public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
