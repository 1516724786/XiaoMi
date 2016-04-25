package arrol.com.xiaomi.biz.registerBiz;

import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/3/29.
 */
public interface OnRegisterListener {
    void registerSuccess(BmobUser user);
    void registerFailed(String s);
}
