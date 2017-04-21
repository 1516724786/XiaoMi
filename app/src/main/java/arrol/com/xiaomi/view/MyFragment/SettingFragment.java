package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import arrol.com.xiaomi.LoginActivity;
import arrol.com.xiaomi.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

/**
 * Created by User on 2016/4/7.
 *
 */
public class SettingFragment extends Fragment{

    @Bind(R.id.bt_exit_setting)
    Button exit;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initEvent();

    }

    private void initEvent(){
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut(getActivity());
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
