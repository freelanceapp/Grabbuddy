package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import grabbuddy.infobite.grabbuddy.modal.api_model.SignUpModel;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealDataList;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealMainModal;
import grabbuddy.infobite.grabbuddy.modal.tokan_responce.TokenModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.LoginActivity;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import grabbuddy.infobite.grabbuddy.utils.FragmentUtils;
import retrofit2.Response;

import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_ID;
import static grabbuddy.infobite.grabbuddy.ui.activities.SplashScreenActivity.android_id;
import static grabbuddy.infobite.grabbuddy.ui.activities.SplashScreenActivity.refreshedToken;

public class HomeFragment extends BaseFragment implements FragmentChangeListener, View.OnClickListener {

    private View rootView;
    private FragmentManager fragmentManager;
    private Activity activity;
    private int fragmentId = 0;
    private List<TodayDealDataList> dealDataLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        init();
        tokenApi();
        return rootView;
    }

    private void init() {
        fragmentManager = (getActivity()).getSupportFragmentManager();
        rootView.findViewById(R.id.llCoupon).setOnClickListener(this);
        rootView.findViewById(R.id.llDeals).setOnClickListener(this);
        rootView.findViewById(R.id.llStylesStudio).setOnClickListener(this);

        changeFragment(new CouponsFragment(), Constant.CouponFragment);

        todayDealApi();
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
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("deals_list", (ArrayList<? extends Parcelable>) dealDataLists);
                    DealsFragment dealsFragment = new DealsFragment();
                    dealsFragment.setArguments(bundle);
                    changeFragment(dealsFragment, Constant.DealsFragment);
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
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.right_enter, R.anim.left_out);
        transaction.replace(R.id.frame_home_items, fragment, strTag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void todayDealApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getTodayDeal(new Dialog(mContext), retrofitApiClient.todayDealData(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    TodayDealMainModal dealMainModal = (TodayDealMainModal) result.body();
                    dealDataLists.clear();
                    if (dealMainModal == null)
                        return;
                    dealDataLists.addAll(dealMainModal.getData());
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    private void tokenApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getToken1(new Dialog(mContext), retrofitApiClient.getToken(USER_ID,android_id,refreshedToken), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    TokenModel sign = (TokenModel) result.body();
                    assert sign != null;
                        Alerts.show(mContext, sign.getMessage());
                        /*if (offerMainModal.getMessage().equals("User is Not Verified")) {
                           // startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
                          //activity.finish();
                       }*/
                }
                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });

        } else {
            cd.show(mContext);
        }
    }



}