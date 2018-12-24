package grabbuddy.infobite.grabbuddy.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;
import grabbuddy.infobite.grabbuddy.utils.FragmentUtils;

import static grabbuddy.infobite.grabbuddy.constant.Constant.MY_PREFS_NAME;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_EMAIL;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_ID;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_MOBILE;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_NAME;
import static grabbuddy.infobite.grabbuddy.utils.FragmentUtils.HOME_FRAGMENT;
import static grabbuddy.infobite.grabbuddy.utils.FragmentUtils.HOME_ID;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isPressedExit = false;
    private FragmentUtils fragmentUtils;
    private int currentPos = 0;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentUtils = FragmentUtils.initFragments(MainActivity.this);
        fragmentUtils.inflateFragment(null, HOME_ID, HOME_FRAGMENT, false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        USER_ID = prefs.getString("user_id", "0");//"No name defined" is the default value.
        USER_NAME = prefs.getString("name", "abc"); //0 is the default value.
        USER_MOBILE = prefs.getString("number","123");
        USER_EMAIL = prefs.getString("email","abc@gmail.com");

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            if (currentPos == 0)
                return true;
            currentPos = 0;
            item.setChecked(true);
            int backStackCount = fragmentUtils.manager.getBackStackEntryCount();
            for (int i = 0; i < backStackCount; i++) {
                fragmentUtils.manager.popBackStack();
            }
            setTitle("Home");
            fragmentUtils.inflateFragment(null, HOME_ID,
                    HOME_FRAGMENT, false);
        } else if (id == R.id.all_stores) {
            if (currentPos == 1)
                return true;
            item.setChecked(true);
            currentPos = 1;
            setTitle("All Stores");
            fragmentUtils.inflateFragment(null, FragmentUtils.ALL_STORES,
                    FragmentUtils.ALL_STORES_FRAGMENT, false);
        } else if (id == R.id.all_categories) {
            if (currentPos == 2)
                return true;
            item.setChecked(true);
            currentPos = 2;
            setTitle("All Categories");
            fragmentUtils.inflateFragment(null, FragmentUtils.ALL_CATEGORIES,
                    FragmentUtils.ALL_CATEGORIES_FRAGMENT, false);
        } else if (id == R.id.steal_deal) {
            if (currentPos == 3)
                return true;
            item.setChecked(true);
            currentPos = 3;
            setTitle("Steal Deal");
            fragmentUtils.inflateFragment(null, FragmentUtils.STEAL_DEAL,
                    FragmentUtils.STEAL_DEAL_FRAGMENT, false);
        } else if (id == R.id.feedback) {
            if (currentPos == 4)
                return true;
            item.setChecked(true);
            currentPos = 4;
            setTitle("FAQ");
            fragmentUtils.inflateFragment(null, FragmentUtils.FEEDBACK,
                    FragmentUtils.FEEDBACK_FRAGMENT, false);
        } else if (id == R.id.privacy) {
            if (currentPos == 5)
                return true;
            item.setChecked(true);
            currentPos = 5;
            setTitle("Privacy Policy");
            fragmentUtils.inflateFragment(null, FragmentUtils.PRIVACY_POLICY,
                    FragmentUtils.PRIVACY_POLICY_FRAGMENT, false);
        } else if (id == R.id.terms_conditions) {
            if (currentPos == 6)
                return true;
            item.setChecked(true);
            currentPos = 6;
            setTitle("Terms & Conditions");
            fragmentUtils.inflateFragment(null, FragmentUtils.TERMS_CONDITIONS,
                    FragmentUtils.TERMS_CONDITIONS_FRAGMENT, false);
        } else if (id == R.id.about_us) {
            if (currentPos == 7)
                return true;
            item.setChecked(true);
            currentPos = 7;
            setTitle("About Us");
            fragmentUtils.inflateFragment(null, FragmentUtils.ABOUT,
                    FragmentUtils.ABOUT_FRAGMENT, false);
        } else if (id == R.id.contact_us) {
            if (currentPos == 8)
                return true;
            item.setChecked(true);
            currentPos = 8;
            setTitle("Contact Us");
            fragmentUtils.inflateFragment(null, FragmentUtils.CONTACT_US,
                    FragmentUtils.CONTACT_US_FRAGMENT, false);
        }
         else if (id == R.id.share) {
            if (currentPos == 10)
                return true;
            item.setChecked(true);
            currentPos = 10;
            setTitle("Share the App");

            fragmentUtils.inflateFragment(null, FragmentUtils.SHARE_APP1,
                    FragmentUtils.SHARE_APP_FRAGMENT1, false);

        }else if (id == R.id.logout) {
            if (currentPos == 11)
                return true;
            item.setChecked(true);
            currentPos = 11;
           logout();
        }
        else if (id == R.id.subscribe) {
            if (currentPos == 9)
                return true;
            item.setChecked(true);
            currentPos = 9;
             fragmentUtils.inflateFragment(null, FragmentUtils.SHARE_APP,
                    FragmentUtils.SHARE_APP_FRAGMENT, false);
            setTitle("Subscribe");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void logout() {
        new AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure want to logout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("user_id","0");
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentPos == 0) {
                if (isPressedExit) {
                    super.onBackPressed();
                } else {
                    isPressedExit = true;
                    Toast.makeText(mContext, "Press again to exit.", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isPressedExit = false;
                        }
                    }, 2000);
                }
            } else {
                int backStackCount = fragmentUtils.manager.getBackStackEntryCount();
                if (backStackCount > 0) {
                    fragmentUtils.manager.popBackStack();
                } else {
                    onNavigationItemSelected(navigationView.getMenu().findItem(R.id.home));
                }
            }
        }
    }

}
