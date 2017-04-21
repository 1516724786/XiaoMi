package arrol.com.xiaomi.biz.totalBiz;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import arrol.com.xiaomi.bean.MonthTotalIn;
import arrol.com.xiaomi.bean.MonthTotalOut;
import arrol.com.xiaomi.biz.homeBiz.ILoadingListener;
import arrol.com.xiaomi.biz.loginBiz.OnLoginListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by User on 2017/4/20.
 */

public class TotalBiz implements ITotalBiz{

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    @Override
    public void LoadingOut(final Context context, final List<Map<String, Object>> data, final ILoadingListener listener) {
        final BmobQuery<MonthTotalOut> query = new BmobQuery<>();
        query.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        query.setLimit(8);
        query.order("-createdAt");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<MonthTotalOut>() {
                    @Override
                    public void onSuccess(List<MonthTotalOut> list) {
                        for (MonthTotalOut out :list){
                            Map<String,Object> map = new HashMap<String, Object>();
                            map.put("outMoney",out.getTotalMoney());
                            map.put("time",out.getTime());
                            data.add(map);
                        }
                        listener.LoadingSuccess();
                    }

                    @Override
                    public void onError(int i, String s) {
                        listener.LoadingFailed(s);
                    }
                });
            }
        });

    }

    @Override
    public void LoadingIn(final Context context, final List<Map<String, Object>> data, final ILoadingListener listener) {
        final BmobQuery<MonthTotalIn> query = new BmobQuery<>();
        query.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        query.setLimit(8);
        query.order("-createdAt");
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<MonthTotalIn>() {
                    @Override
                    public void onSuccess(List<MonthTotalIn> list) {
                        for (MonthTotalIn in :list){
                            Map<String,Object> map = new HashMap<String, Object>();
                            map.put("inMoney",in.getTotalMoney());
                            data.add(map);
                        }
                        listener.LoadingSuccess();
                    }

                    @Override
                    public void onError(int i, String s) {
                        listener.LoadingFailed(s);
                    }
                });
            }
        });

    }
}
