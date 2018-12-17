package grabbuddy.infobite.grabbuddy.ui.activities;

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
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;
import grabbuddy.infobite.grabbuddy.utils.FragmentUtils;

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
            setTitle("Feedback");
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
        } else if (id == R.id.share) {
            if (currentPos == 9)
                return true;
            item.setChecked(true);
            currentPos = 9;
            setTitle("Share the App");
            fragmentUtils.inflateFragment(null, FragmentUtils.SHARE_APP,
                    FragmentUtils.SHARE_APP_FRAGMENT, false);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
