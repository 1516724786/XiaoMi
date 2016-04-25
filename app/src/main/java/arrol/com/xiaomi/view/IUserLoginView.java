package arrol.com.xiaomi.view;

import android.content.Context;

import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/3/26.
 */
public interface IUserLoginView {
    String getUserName();
    String getPassword();
    void toRegisterActivity();
    void showLoading();
    void hideLoading();
    void  toMainActivity(BmobUser user);
    void showFailedError(String s);
    Context getContext();
}
