package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.customView.PieView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/4/7.
 */
public class ShowFragment extends Fragment{

    @Bind(R.id.pieView_show)
    PieView pieView;

    private View  view;

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

        pieView.setitemsValues(new Double[]{5d, 4d, 4d, 3d, 2d});
        pieView.setStrokeColor("#ffffff");
    }

}
