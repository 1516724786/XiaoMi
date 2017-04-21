package arrol.com.xiaomi.biz.homeBiz;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import arrol.com.xiaomi.bean.Record;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by User on 2016/6/2.
 *
 */
public class HomeFragmentBiz implements IHomeFragmentBiz{

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    @Override
    public void LoadingOut(final Context context, final List<Map<String, Object>> data, int pageNumber, int state, final ILoadingListener listener) {
        BmobQuery<Record> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        final BmobQuery<Record> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("kind", "支出");
        List<BmobQuery<Record>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        final BmobQuery<Record> query = new BmobQuery<>();
        query.and(queries);
        query.setLimit(8);
        query.setSkip(8 * pageNumber);
        query.order("-createdAt");
        if(state == 1){
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        }
        else{
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<Record>() {
                    @Override
                    public void onSuccess(List<Record> list) {
                        for (Record record : list) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("category", record.getCategory());
                            map.put("time", record.getTime());
                            map.put("money", record.getMoney());
                            map.put("remark", record.getRemark());
                            data.add(map);
                        }
                        listener.LoadingSuccess();
                    }

                    @Override
                    public void onError(int i, String s) {
                        /**
                         * i = 9009 表示本地没有缓存数据的错误
                         */
                        if (i != 9009) {
                            listener.LoadingFailed(s);
                        }

                    }
                });
            }
        });
    }

    @Override
    public void RefreshOut(final Context context, final List<Map<String, Object>> data, int pageNumber, final ILoadingListener listener) {
        BmobQuery<Record> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        final BmobQuery<Record> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("kind", "支出");
        List<BmobQuery<Record>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        final BmobQuery<Record> query = new BmobQuery<>();
        query.and(queries);
        query.setLimit(8);
        query.setSkip(8 * pageNumber);
        query.order("-createdAt");
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.clearCachedResult(context, Record.class);
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<Record>() {
                    @Override
                    public void onSuccess(List<Record> list) {
                        data.clear();
                        for (Record record : list) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("category", record.getCategory());
                            map.put("time", record.getTime());
                            map.put("money", record.getMoney());
                            map.put("remark", record.getRemark());
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
    public void LoadingIn(final Context context, final List<Map<String, Object>> data,
                          int pageNumber, int state, final int distinguish,
                          final ILoadingListener listener) {
        BmobQuery<Record> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        BmobQuery<Record> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("kind", "收入");
        List<BmobQuery<Record>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        final BmobQuery<Record> query = new BmobQuery<>();
        query.and(queries);
        query.setLimit(8);
        query.setSkip(8 * pageNumber);
        query.order("-createdAt");
        if(state == 1){
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        }
        else{
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<Record>() {
                    @Override
                    public void onSuccess(List<Record> list) {
                        if (distinguish == 1) {
//                            data.clear();
                        }
                        for (Record record : list) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("time", record.getTime());
                            map.put("money", record.getMoney());
                            map.put("remark", record.getRemark());
                            data.add(map);
                        }
                        listener.LoadingSuccess();
                    }

                    @Override
                    public void onError(int i, String s) {
                        /**
                         * i = 9009 表示本地没有缓存数据的错误
                         */
                        if (i != 9009) {
                            listener.LoadingFailed(s);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void RefreshIn(final Context context, final List<Map<String, Object>> data, int pageNumber, final ILoadingListener listener) {
        BmobQuery<Record> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("user", BmobUser.getCurrentUser(context, BmobUser.class));
        BmobQuery<Record> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("kind", "收入");
        List<BmobQuery<Record>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);
        final BmobQuery<Record> query = new BmobQuery<>();
        query.and(queries);
        query.setLimit(8);
        query.setSkip(8 * pageNumber);
        query.order("-createdAt");
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.clearCachedResult(context, Record.class);
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                query.findObjects(context, new FindListener<Record>() {
                    @Override
                    public void onSuccess(List<Record> list) {
                        data.clear();
                        for (Record record : list) {
                            Map<String,Object> map =new HashMap<>();
                            map.put("time",record.getTime());
                            map.put("money",record.getMoney());
                            map.put("remark",record.getRemark());
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
