package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.OffersAdapter;
import grabbuddy.infobite.grabbuddy.adapter.PopularStoresAdapter;
import grabbuddy.infobite.grabbuddy.adapter.SlidingImage_Adapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.FragmentUtils;

public class HomeFragment extends BaseFragment implements FragmentChangeListener, View.OnClickListener {

    private View rootView;
    private FragmentManager fragmentManager;
    private Activity activity;
    private int fragmentId = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        activity = getActivity();

        init();
        return rootView;
    }

    private void init() {
        fragmentManager = (getActivity()).getSupportFragmentManager();
        rootView.findViewById(R.id.llCoupon).setOnClickListener(this);
        rootView.findViewById(R.id.llDeals).setOnClickListener(this);
        rootView.findViewById(R.id.llStylesStudio).setOnClickListener(this);

        changeFragment(new CouponsFragment(), Constant.CouponFragment);
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {
        if (fragmentManager != null)
            init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llCoupon:
                if (fragmentId != 0) {
                    changeFragment(new CouponsFragment(), Constant.CouponFragment);
                }
                fragmentId = 0;
                rootView.findViewById(R.id.viewCoupon).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.viewDeals).setVisibility(View.GONE);
                rootView.findViewById(R.id.viewStylesStudio).setVisibility(View.GONE);
                break;
            case R.id.llDeals:
                if (fragmentId != 1) {
                    changeFragment(new DealsFragment(), Constant.DealsFragment);
                }
                fragmentId = 1;
                rootView.findViewById(R.id.viewCoupon).setVisibility(View.GONE);
                rootView.findViewById(R.id.viewDeals).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.viewStylesStudio).setVisibility(View.GONE);
                break;
            case R.id.llStylesStudio:
                if (fragmentId != 2) {
                    changeFragment(new StylesStudioFragment(), Constant.StylesStudioFragment);
                }
                fragmentId = 2;
                rootView.findViewById(R.id.viewCoupon).setVisibility(View.GONE);
                rootView.findViewById(R.id.viewDeals).setVisibility(View.GONE);
                rootView.findViewById(R.id.viewStylesStudio).setVisibility(View.VISIBLE);
                break;
        }
    }

    private void changeFragment(Fragment fragment, String strTag) {
        /*fragmentManager
                .beginTransaction()
                .replace(R.id.frame_home_items, fragment,
                        strTag).commit();*/
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.right_enter, R.anim.left_out);
        transaction.replace(R.id.frame_home_items, fragment, strTag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}