package grabbuddy.infobite.grabbuddy.utils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentService;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.ui.fragment.AboutUsFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.AllCategoriesFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.ContactUsFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.FeedbackFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.HomeFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.PrivacyFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.ShareAppFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.ShareAppFragment1;
import grabbuddy.infobite.grabbuddy.ui.fragment.StealDealFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.TermConditionFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.TopStoresFragment;

public class FragmentUtils implements FragmentService {

    public static final int HOME_ID = 0;
    public static final int ALL_STORES = 1;
    public static final int ALL_CATEGORIES = 2;
    public static final int STEAL_DEAL = 3;
    public static final int FEEDBACK = 4;
    public static final int PRIVACY_POLICY = 5;
    public static final int TERMS_CONDITIONS = 6;
    public static final int ABOUT = 7;
    public static final int CONTACT_US = 8;
    public static final int SHARE_APP = 9;
    public static final int SHARE_APP1 = 10;

    public static final String HOME_FRAGMENT = "home";
    public static final String ALL_STORES_FRAGMENT = "ALL_STORES_FRAGMENT";
    public static final String ALL_CATEGORIES_FRAGMENT = "ALL_CATEGORIES_FRAGMENT";
    public static final String STEAL_DEAL_FRAGMENT = "STEAL_DEAL_FRAGMENT";
    public static final String FEEDBACK_FRAGMENT = "FEEDBACK_FRAGMENT";
    public static final String PRIVACY_POLICY_FRAGMENT = "PRIVACY_POLICY_FRAGMENT";
    public static final String TERMS_CONDITIONS_FRAGMENT = "TERMS_CONDITIONS_FRAGMENT";
    public static final String ABOUT_FRAGMENT = "ABOUT_FRAGMENT";
    public static final String CONTACT_US_FRAGMENT = "CONTACT_US_FRAGMENT";
    public static final String SHARE_APP_FRAGMENT = "SHARE_APP_FRAGMENT";
    public static final String SHARE_APP_FRAGMENT1 = "SHARE_APP_FRAGMENT";

    public static FragmentUtils mInstance;
    private static MainActivity mainActivity;
    public FragmentManager manager;
    private int lastCommit = -1;
    private RegisterFragmentHandler fragmentHandler;
    private Fragment fragmentArray[];
    private String[] tags = {HOME_FRAGMENT, ALL_STORES_FRAGMENT, ALL_CATEGORIES_FRAGMENT,
            STEAL_DEAL_FRAGMENT, FEEDBACK_FRAGMENT, PRIVACY_POLICY_FRAGMENT, TERMS_CONDITIONS_FRAGMENT,
            ABOUT_FRAGMENT, CONTACT_US_FRAGMENT, SHARE_APP_FRAGMENT, SHARE_APP_FRAGMENT1};

    public static FragmentUtils initFragments(MainActivity act) {
        mInstance = new FragmentUtils();
        mainActivity = act;
        mInstance.showFragment(null, HOME_ID, HOME_FRAGMENT, false);

        return mInstance;
    }

    private void showFragment(Bundle bundle, int fragmentId, String tag, boolean isBackstack) {
        manager = mainActivity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (fragmentArray == null) {
            fragmentHandler = new RegisterFragmentHandler();

            fragmentArray = new Fragment[]{
                    new HomeFragment(),
                    new TopStoresFragment(),
                    new AllCategoriesFragment(),
                    new StealDealFragment(),
                    new FeedbackFragment(),
                    new PrivacyFragment(),
                    new TermConditionFragment(),
                    new AboutUsFragment(),
                    new ContactUsFragment(),
                    new ShareAppFragment(),
                    new ShareAppFragment1()
            };

            for (int i = 0; i < fragmentArray.length; i++) {
                fragmentHandler.registerFragment((FragmentChangeListener) fragmentArray[i]);
                ft.add(R.id.container_main, fragmentArray[i], tags[i]);
                ft.hide(fragmentArray[i]);
            }
        }
        if (lastCommit != -1)
            ft.hide(fragmentArray[lastCommit]);
        ft.show(fragmentArray[fragmentId]);
        lastCommit = fragmentId;

        if (isBackstack)
            ft.addToBackStack(tag);

        if (bundle != null)
            fragmentArray[fragmentId].setArguments(bundle);

        ft.commit();
        fragmentHandler.notifyFragment(fragmentId);
    }

    public void showChildFragment(Fragment fragment, Bundle bundle, int container) {
        FragmentTransaction ft = manager.beginTransaction();
        fragment.setArguments(bundle);
        ft.add(container, fragment, null);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void inflateFragment(Bundle bundle, int fragmentId, String tag, boolean isBackstack) {
        showFragment(bundle, fragmentId, tag, isBackstack);
    }

    public class RegisterFragmentHandler {
        ArrayList<FragmentChangeListener> listeners = new ArrayList<>();

        public void registerFragment(FragmentChangeListener listener) {
            listeners.add(listener);
        }

        public void notifyFragment(int fragmentId) {
            listeners.get(fragmentId).onFragmentVisible("Fragment Visible");
        }

        public void notifyAllFragment(String tag) {
            for (FragmentChangeListener listener : listeners) {
                listener.onFragmentVisible(tag);
            }
        }
    }
}