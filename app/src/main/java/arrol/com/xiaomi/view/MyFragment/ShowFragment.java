package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.adapter.AdapterTotal;
import arrol.com.xiaomi.customView.PieView;
import arrol.com.xiaomi.presenter.ShowFragmentPresenter;
import arrol.com.xiaomi.view.IShowFragmentView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 */
public class ShowFragment extends Fragment implements IShowFragmentView{

    @Bind(R.id.fragment_show_listView)
    ListView listView;
    @Bind(R.id.fragment_show_lnly)
    LinearLayout linearLayout;

    private View  view;
    private List<Map<String,Object>> totalData;
    private AdapterTotal adapterTotal;
    private ShowFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_show,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        presenter = new ShowFragmentPresenter(this);

        totalData = new ArrayList<>();
        adapterTotal = new AdapterTotal(getActivity(),totalData);
        listView.setAdapter(adapterTotal);

        presenter.LoadingTotal();

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showFailedError(String s) {
        Toast.makeText(getActivity(), "Error:" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Map<String, Object>> getListTotal() {
        return totalData;
    }

    @Override
    public void changedData() {
        adapterTotal.notifyDataSetChanged();
        linearLayout.setVisibility(View.GONE);
    }
}
