package arrol.com.xiaomi.view;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/6/1.
 */
public interface IHomeFragmentView {
    Context getContext();
    void showFailedError(String s);
    List<Map<String,Object>> getListOut();
    List<Map<String,Object>> getListIn();
    void changedDataOut();
    void changedDataIn();

}
