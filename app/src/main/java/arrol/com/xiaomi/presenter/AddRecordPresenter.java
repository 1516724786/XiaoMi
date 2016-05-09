package arrol.com.xiaomi.presenter;


import android.os.Handler;

import arrol.com.xiaomi.biz.addRecordBiz.AddRecordBiz;
import arrol.com.xiaomi.biz.addRecordBiz.IPostDataListener;
import arrol.com.xiaomi.view.IAddRecordView;

/**
 * Created by User on 2016/5/9.
 */
public class AddRecordPresenter {

    private IAddRecordView addRecordView;
    private AddRecordBiz addRecordBiz;
    private Handler handler=new Handler();

    public AddRecordPresenter(IAddRecordView addRecordView){
        this.addRecordView=addRecordView;
        this.addRecordBiz=new AddRecordBiz();
    }

    public void addRecord(){
        addRecordView.showLoading();
        addRecordBiz.postData(addRecordView.getContext(), addRecordView.getRecord(), new IPostDataListener() {
            @Override
            public void postSuccess() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        addRecordView.hideLoading();
                        addRecordView.toMainActivity();
                    }
                });
            }

            @Override
            public void postFailed(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        addRecordView.hideLoading();
                        addRecordView.showFailedError(s);
                    }
                });
            }
        });
    }
}
