package arrol.com.xiaomi.presenter;

import android.os.Handler;

import arrol.com.xiaomi.bean.User;
import arrol.com.xiaomi.biz.registerBiz.OnRegisterListener;
import arrol.com.xiaomi.biz.registerBiz.RegisterBiz;
import arrol.com.xiaomi.view.IRegisterView;
import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/3/29.
 */
public class RegisterPresenter {
    private IRegisterView registerView;
    private RegisterBiz registerBiz;
    private Handler handler=new Handler();
    public RegisterPresenter(IRegisterView registerView){
        this.registerView=registerView;
        this.registerBiz=new RegisterBiz();
    }
    public void register(){
        registerView.showLoading();
        registerBiz.register(registerView.getContext(),registerView.getUserName(),
                registerView.getPassword(), registerView.getPasswordAgain(),
                new OnRegisterListener() {
            @Override
            public void registerSuccess(final BmobUser user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.toMainActivity(user);
                        registerView.hideLoading();
                    }
                });
            }

            @Override
            public void registerFailed(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.hideLoading();
                        registerView.showFailedError(s);
                    }
                });
            }
        });
    }
}
