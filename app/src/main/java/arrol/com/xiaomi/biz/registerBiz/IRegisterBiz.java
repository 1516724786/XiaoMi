package arrol.com.xiaomi.biz.registerBiz;

import android.content.Context;

/**
 * Created by User on 2016/3/29.
 */
public interface IRegisterBiz {
    void register(Context context,String userName,String password,String password_again,OnRegisterListener registerListener);
}
