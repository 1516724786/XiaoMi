package arrol.com.xiaomi.view.MyFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arrol.com.xiaomi.R;

/**
 * Created by User on 2016/4/7.
 */
public class AddNoteFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }
}
