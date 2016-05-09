package arrol.com.xiaomi.biz.addRecordBiz;

import android.content.Context;

import arrol.com.xiaomi.bean.Record;

/**
 * Created by User on 2016/5/9.
 */
public interface IAddRecordBiz {
    void postData(Context context,Record record,IPostDataListener listener);
}
