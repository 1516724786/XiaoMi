package arrol.com.xiaomi.presenter;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.biz.homeBiz.ILoadingListener;
import arrol.com.xiaomi.biz.totalBiz.TotalBiz;
import arrol.com.xiaomi.view.IShowFragmentView;

/**
 * Created by User on 2017/4/20.
 */

public class ShowFragmentPresenter {

    private TotalBiz totalBiz;
    private IShowFragmentView iShowFragmentView;

    private List<Map<String,Object>> totalIn = new ArrayList<>();
    private List<Map<String,Object>> totalOut = new ArrayList<>();
    private Handler handler = new Handler();

    public ShowFragmentPresenter(IShowFragmentView iShowFragmentView){
        this.totalBiz = new TotalBiz();
        this.iShowFragmentView = iShowFragmentView;
    }

    public void LoadingTotal(){
        totalBiz.LoadingOut(iShowFragmentView.getContext(), totalOut, new ILoadingListener() {
            @Override
            public void LoadingSuccess() {
                LoadingTotalIn();
            }

            @Override
            public void LoadingFailed(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowFragmentView.showFailedError(s);
                    }
                });
            }
        });
    }
    private void LoadingTotalIn(){
        totalBiz.LoadingIn(iShowFragmentView.getContext(), totalIn, new ILoadingListener() {
            @Override
            public void LoadingSuccess() {
                MergeData();
            }

            @Override
            public void LoadingFailed(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowFragmentView.showFailedError(s);
                    }
                });
            }
        });
    }
    private void MergeData(){
        if (totalIn.size() != totalOut.size())
        {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iShowFragmentView.showFailedError("There is something wrong");
                }
            });
        }
        else {
            for (int i=0; i<totalOut.size(); i++){
                Map<String,Object> map = new HashMap<>();
                map.put("time",totalOut.get(i).get("time"));
                map.put("outMoney",totalOut.get(i).get("outMoney"));
                map.put("inMoney",totalIn.get(i).get("inMoney"));
                iShowFragmentView.getListTotal().add(map);
                Log.i("time",totalOut.get(i).get("time").toString());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iShowFragmentView.changedData();
                }
            });
        }
    }
}
