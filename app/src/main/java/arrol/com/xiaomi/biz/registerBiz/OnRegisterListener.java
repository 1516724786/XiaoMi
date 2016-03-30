package arrol.com.xiaomi.biz.registerBiz;

import arrol.com.xiaomi.bean.User;

/**
 * Created by User on 2016/3/29.
 */
public interface OnRegisterListener {
    void registerSuccess(User user);
    void registerFailed();
}
