package grabbuddy.infobite.grabbuddy.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.fragment.tab_fragment.AllCouponFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.tab_fragment.CodesTypeFragment;
import grabbuddy.infobite.grabbuddy.ui.fragment.tab_fragment.DealTypeFragment;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryDetailActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context mContext;
    private String strId = "", strName = "", strLogo = "";
    private List<CategoryWiseDatum> categoryWiseList = new ArrayList<>();
    private List<CategoryWiseDatum> categoryWiseCodeList = new ArrayList<>();
    private List<CategoryWiseDatum> categoryWiseDealsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        if (getIntent() == null)
            return;

        ((ImageView) findViewById(R.id.imgBack)).setOnClickListener(this);

        strId = getIntent().getStringExtra("id");
        strName = getIntent().getStringExtra("name");
        strLogo = getIntent().getStringExtra("logo");

        AppPreference.setStringPreference(mContext, Constant.IMAGE_PREF, strLogo);

        ((TextView) findViewById(R.id.tvName)).setText(strName);
        Picasso.with(mContext)
                .load(Constant.IMAGE3 + strLogo)
                .placeholder(R.drawable.app_logo_b)
                .into(((ImageView) findViewById(R.id.img)));
        categoryWiseCouponApi();

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    /* Set viewpager */
    private void setupViewPager(ViewPager viewPager) {
        Bundle bundleAll = new Bundle();
        Bundle bundleDeals = new Bundle();
        Bundle bundleCodes = new Bundle();

        bundleAll.putParcelableArrayList("allCoupon", (ArrayList<? extends Parcelable>) categoryWiseList);
        bundleDeals.putParcelableArrayList("deals", (ArrayList<? extends Parcelable>) categoryWiseDealsList);
        bundleCodes.putParcelableArrayList("code", (ArrayList<? extends Parcelable>) categoryWiseCodeList);

        AllCouponFragment allCouponFragment = new AllCouponFragment();
        DealTypeFragment dealTypeFragment = new DealTypeFragment();
        CodesTypeFragment codesTypeFragment = new CodesTypeFragment();
        allCouponFragment.setArguments(bundleAll);
        dealTypeFragment.setArguments(bundleDeals);
        codesTypeFragment.setArguments(bundleCodes);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(allCouponFragment, "All");
        adapter.addFragment(dealTypeFragment, "Deals");
        adapter.addFragment(codesTypeFragment, "Codes");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void categoryWiseCouponApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getCategoryWiseData(new Dialog(mContext), retrofitApiClient.categoryWiseData(strId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CategoryWiseMainModal mainModal = (CategoryWiseMainModal) result.body();
                    if (mainModal != null) {
                        categoryWiseList.addAll(mainModal.getData());
                        splitData();
                    } else {
                        Alerts.show(mContext, "Data not found!!!");
                    }
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

    private void splitData() {
        if (categoryWiseList.size() > 0) {
            for (int i = 0; i < categoryWiseList.size(); i++) {
                if (categoryWiseList.get(i).getCouponType().equalsIgnoreCase("deals")) {
                    CategoryWiseDatum dealsList = categoryWiseList.get(i);
                    categoryWiseDealsList.add(dealsList);
                } else if (categoryWiseList.get(i).getCouponType().equalsIgnoreCase("coupon")) {
                    CategoryWiseDatum couponList = categoryWiseList.get(i);
                    categoryWiseCodeList.add(couponList);
                }
            }
        }
        setupViewPager(viewPager);
    }
}
