package arrol.com.xiaomi.biz.homeBiz;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/6/2.
 */
public interface IHomeFragmentBiz {
    void LoadingOut(Context context, List<Map<String, Object>> data, int pageNumber,
                    int state, ILoadingListener listener);
    void RefreshOut(Context context, List<Map<String, Object>> data, int pageNumber,
                    ILoadingListener listener);

    void LoadingIn(Context context, List<Map<String,Object>> data, int pageNumber,
                   int state, int distinguish,ILoadingListener listener);
    void RefreshIn(Context context, List<Map<String, Object>> data, int pageNumber,
                   ILoadingListener listener);

}
