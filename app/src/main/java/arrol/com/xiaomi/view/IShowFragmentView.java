package arrol.com.xiaomi.view;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 2017/4/20.
 */

public interface IShowFragmentView {
    Context getContext();
    void showFailedError(String s);
    List<Map<String,Object>> getListTotal();
    void changedData();
}
