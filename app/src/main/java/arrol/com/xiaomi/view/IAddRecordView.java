package arrol.com.xiaomi.view;

import android.content.Context;

import arrol.com.xiaomi.bean.Record;

/**
 * Created by User on 2016/5/9.
 */
public interface IAddRecordView {

    Context getContext();
    Record getRecord();
    void showLoading();
    void hideLoading();
    void toMainActivity();
    void showFailedError(String s);
    boolean isReady();
}
