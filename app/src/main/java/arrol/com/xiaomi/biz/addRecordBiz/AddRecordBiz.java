package arrol.com.xiaomi.biz.addRecordBiz;

import android.content.Context;

import arrol.com.xiaomi.bean.Record;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by User on 2016/5/9.
 */
public class AddRecordBiz implements IAddRecordBiz{
    @Override
    public void postData(final Context context, final Record record, final IPostDataListener listener) {
        new Thread(){
            @Override
            public void run(){
                record.save(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        listener.postSuccess();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        listener.postFailed(s);
                    }
                });
            }
        }.start();
    }
}
