package arrol.com.xiaomi.view;

import android.content.Context;

import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/3/29.
 */
public interface IRegisterView {
    Context getContext();
    String getUserName();
    String getPassword();
    String getPasswordAgain();
    void showLoading();
    void hideLoading();
    void  toMainActivity(BmobUser user);
    void toLoginActivity();
    void showFailedError(String s);
}
