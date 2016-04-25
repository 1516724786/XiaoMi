package arrol.com.xiaomi.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.view.MyFragment.AddAlertFragment;
import arrol.com.xiaomi.view.MyFragment.AddNoteFragment;
import arrol.com.xiaomi.view.MyFragment.ContactsFragment;
import arrol.com.xiaomi.view.MyFragment.HomeFragment;
import arrol.com.xiaomi.view.MyFragment.SettingFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.navigation_main) NavigationView navigationView;
    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;

    private AddNoteFragment noteFragment;
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private ContactsFragment contactsFragment;
    private AddAlertFragment alertFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setDefaultFragment();
        initView();

    }
    public void setDefaultFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        homeFragment=new HomeFragment();
        fragmentTransaction.replace(R.id.frameLayout_main, homeFragment);
        fragmentTransaction.commit();
    }

    public void initView(){
        final FragmentManager fragmentManager=getFragmentManager();

        homeFragment=new HomeFragment();
        noteFragment=new AddNoteFragment();
        settingFragment=new SettingFragment();
        alertFragment=new AddAlertFragment();
        contactsFragment=new ContactsFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.ic_home:
                        fragmentTransaction.replace(R.id.frameLayout_main, homeFragment);
                        break;
                    case R.id.ic_note_add:
                        fragmentTransaction.replace(R.id.frameLayout_main, noteFragment);
                        break;
                    case R.id.ic_settings:
                        fragmentTransaction.replace(R.id.frameLayout_main, settingFragment);
                        break;
                    case R.id.ic_add_alert:
                        fragmentTransaction.replace(R.id.frameLayout_main,alertFragment);
                        break;
                    case R.id.ic_contacts:
                        fragmentTransaction.replace(R.id.frameLayout_main,contactsFragment);
                        break;
                }
                fragmentTransaction.commit();
                drawerLayout.closeDrawers();
                return true;

            }
        });
    }
}
