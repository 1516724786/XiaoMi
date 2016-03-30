package arrol.com.xiaomi.biz.registerBiz;

/**
 * Created by User on 2016/3/29.
 */
public interface IRegisterBiz {
    void register(String userName,String password,String password_again,OnRegisterListener registerListener);
}
