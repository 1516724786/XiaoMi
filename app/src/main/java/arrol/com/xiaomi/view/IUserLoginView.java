package arrol.com.xiaomi.view;

import arrol.com.xiaomi.bean.User;

/**
 * Created by User on 2016/3/26.
 */
public interface IUserLoginView {
    String getUserName();
    String getPassword();
    void toRegisterActivity();
    void showLoading();
    void hideLoading();
    void  toMainActivity(User user);
    void showFailedError();
}
