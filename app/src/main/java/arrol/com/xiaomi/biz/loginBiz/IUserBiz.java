package arrol.com.xiaomi.biz.loginBiz;

/**
 * Created by User on 2016/3/26.
 */
public interface IUserBiz {
    void login(String username,String password,OnLoginListener loginListener);
}
