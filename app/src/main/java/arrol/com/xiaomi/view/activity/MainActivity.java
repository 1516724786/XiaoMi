package arrol.com.xiaomi.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arrol.com.xiaomi.R;
import arrol.com.xiaomi.view.MyFragment.AddAlertFragment;
import arrol.com.xiaomi.view.MyFragment.ShowFragment;
import arrol.com.xiaomi.view.MyFragment.SecretFragment;
import arrol.com.xiaomi.view.MyFragment.HomeFragment;
import arrol.com.xiaomi.view.MyFragment.SettingFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.navigation_main) NavigationView navigationView;
    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;
    @Bind(R.id.drawer_header_circleView)CircleImageView circleImageView;
    @Bind(R.id.menu_main)Button menu;
    @Bind(R.id.tv_main_title)TextView title;

    private ShowFragment noteFragment;
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private SecretFragment contactsFragment;
    private AddAlertFragment alertFragment;
    private long lastTime=0;

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

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        final FragmentManager fragmentManager=getFragmentManager();

        homeFragment=new HomeFragment();
        noteFragment=new ShowFragment();
        settingFragment=new SettingFragment();
        alertFragment=new AddAlertFragment();
        contactsFragment=new SecretFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.ic_home:
                        title.setText(R.string.bill);
                        fragmentTransaction.replace(R.id.frameLayout_main, homeFragment);
                        break;
                    case R.id.ic_note_add:
                        title.setText(R.string.bill_total);
                        fragmentTransaction.replace(R.id.frameLayout_main, noteFragment);
                        break;
                    case R.id.ic_settings:
                        title.setText(R.string.setting);
                        fragmentTransaction.replace(R.id.frameLayout_main, settingFragment);
                        break;
                    case R.id.ic_add_alert:
                        title.setText(R.string.memo);
                        fragmentTransaction.replace(R.id.frameLayout_main,alertFragment);
                        break;
                    case R.id.ic_contacts:
                        title.setText(R.string.password_record);
                        fragmentTransaction.replace(R.id.frameLayout_main,contactsFragment);
                        break;
                }
                fragmentTransaction.commit();
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    public void onBackPressed(){
        if(lastTime<=0){
            Toast.makeText(this,"再按一次退出！",Toast.LENGTH_SHORT).show();
            lastTime=System.currentTimeMillis();
        }
        else{
            long clickTime=System.currentTimeMillis();
            if(clickTime-lastTime<1500){
                finish();
            }else{
                Toast.makeText(this,"再按一次退出！",Toast.LENGTH_SHORT).show();
                lastTime=clickTime;
            }
        }
    }


}
