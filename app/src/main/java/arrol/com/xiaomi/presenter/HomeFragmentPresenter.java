package arrol.com.xiaomi.presenter;

import android.os.Handler;

import arrol.com.xiaomi.biz.homeBiz.HomeFragmentBiz;
import arrol.com.xiaomi.biz.homeBiz.ILoadingListener;
import arrol.com.xiaomi.view.IHomeFragmentView;

/**
 * Created by User on 2016/6/2.
 *
 */
public class HomeFragmentPresenter {

    private IHomeFragmentView homeFragmentView;
    private HomeFragmentBiz homeFragmentBiz;
    private Handler handler=new Handler();

    public HomeFragmentPresenter(IHomeFragmentView homeFragmentView){
        this.homeFragmentView=homeFragmentView;
        this.homeFragmentBiz=new HomeFragmentBiz();
    }

    /**
     * 加载支出数据
     * @param pageNumber
     * 分页加载的页数
     * @param state
     * 缓存查询的区分码
     */
    public void LoadingDataOut(int pageNumber, int state){
        homeFragmentBiz.LoadingOut(homeFragmentView.getContext(), homeFragmentView.getListOut(),
                pageNumber, state, new ILoadingListener() {
                    @Override
                    public void LoadingSuccess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.changedDataOut();
                            }
                        });
                    }

                    @Override
                    public void LoadingFailed(final String s) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.showFailedError(s);
                            }
                        });
                    }
                });
    }

    /**
     * 刷新支出数据
     * @param pageNumber
     * 分页加载的页数
     */
    public void RefreshDataOut(int pageNumber){
        homeFragmentBiz.RefreshOut(homeFragmentView.getContext(), homeFragmentView.getListOut(),
                pageNumber, new ILoadingListener() {
                    @Override
                    public void LoadingSuccess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.changedDataOut();
                            }
                        });
                    }

                    @Override
                    public void LoadingFailed(final String s) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.showFailedError(s);
                            }
                        });
                    }
                });
    }

    public void LoadingDataIn(int pageNumber, int state, int distiguish){
        homeFragmentBiz.LoadingIn(homeFragmentView.getContext(), homeFragmentView.getListIn(),
                pageNumber, state, distiguish, new ILoadingListener() {
                    @Override
                    public void LoadingSuccess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.changedDataIn();
                            }
                        });
                    }

                    @Override
                    public void LoadingFailed(final String s) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.showFailedError(s);
                            }
                        });
                    }
                });
    }
    public void RefreshDataIn(int pageNumber){
        homeFragmentBiz.RefreshIn(homeFragmentView.getContext(), homeFragmentView.getListIn(),
                pageNumber, new ILoadingListener() {
                    @Override
                    public void LoadingSuccess() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.changedDataIn();
                            }
                        });
                    }

                    @Override
                    public void LoadingFailed(final String s) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                homeFragmentView.showFailedError(s);
                            }
                        });
                    }
                });
    }
}
