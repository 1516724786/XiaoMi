package arrol.com.xiaomi.biz.totalBiz;

import android.content.Context;

import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.biz.homeBiz.ILoadingListener;

/**
 * Created by User on 2017/4/20.
 */

public interface ITotalBiz {
    void LoadingOut(Context context, List<Map<String, Object>> data,
                    ILoadingListener listener);
    void LoadingIn(Context context, List<Map<String,Object>> data,
                    ILoadingListener listener);
}
