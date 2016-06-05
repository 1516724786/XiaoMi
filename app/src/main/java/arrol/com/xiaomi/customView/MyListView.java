package arrol.com.xiaomi.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2016/5/26.
 *
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener{

    private final int LOADING=1;
    private final int GONE=2;
    private int state = GONE;


    private View footView;

    private boolean isRemark;

    private int sumItem;
    private int totalItem;
    IReFlashListener iReFlashListener;

    public MyListView(Context context) {
        super(context);
        FootView(context);
        ViewChangedByState(state);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        FootView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FootView(context);
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        sumItem=firstVisibleItem+visibleItemCount;
        totalItem=totalItemCount;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(totalItem==sumItem && scrollState==SCROLL_STATE_IDLE){
            if(!isRemark){
                isRemark=true;
                state=LOADING;
                ViewChangedByState(state);
                iReFlashListener.onReFlash();//这里是刷新数据的关键
            }
        }
    }
    private void FootView(Context context){
        LayoutInflater inflater= LayoutInflater.from(context);
        footView=inflater.inflate(R.layout.foot_listview,null);
        this.addFooterView(footView);
        this.setOnScrollListener(this);

    }
    private void ViewChangedByState(int state){
        switch (state){
            case LOADING:
                this.footView.setVisibility(View.VISIBLE);
                this.footView.setPadding(0, 0, 0, 0);
                break;
            case GONE:
                this.footView.setVisibility(View.GONE);
                this.footView.setPadding(0, -footView.getHeight(), 0, 0);
                break;
        }
    }

    public void refreshComplete(){
        state=GONE;
        isRemark=false;
        ViewChangedByState(state);
    }
    public void setInterface(IReFlashListener iReFlashListener){
        this.iReFlashListener=iReFlashListener;
    }
    /**
     * 刷新数据的接口
     */
    public interface IReFlashListener{
        void onReFlash();
    }
}
