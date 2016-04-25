package arrol.com.xiaomi.biz.loginBiz;

import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/3/26.
 */
public interface OnLoginListener {
    void loginSuccess(BmobUser user);
    void loginFailed(String s);
}
