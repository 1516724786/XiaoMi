package arrol.com.xiaomi.biz.loginBiz;

import android.content.Context;

/**
 * Created by User on 2016/3/26.
 */
public interface IUserBiz {
    void login(Context context,String username,String password,OnLoginListener loginListener);
}
