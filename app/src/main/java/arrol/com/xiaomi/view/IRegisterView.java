package arrol.com.xiaomi.view;

import arrol.com.xiaomi.bean.User;

/**
 * Created by User on 2016/3/29.
 */
public interface IRegisterView {
    String getUserName();
    String getPassword();
    String getPasswordAgain();
    void showLoading();
    void hideLoading();
    void  toMainActivity(User user);
    void toLoginActivity();
    void showFailedError();
}
