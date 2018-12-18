package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.PopularStoresAdapter;
import grabbuddy.infobite.grabbuddy.adapter.SlidingImage_Adapter;
import grabbuddy.infobite.grabbuddy.adapter.StylesStudioAdapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.CouponDetailActivity;
import grabbuddy.infobite.grabbuddy.ui.activities.StoreDetailActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class CouponsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private CirclePageIndicator indicator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, R.drawable.img_d};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView recyclerViewPopularStore, recyclerViewTopOffer;
    private TodaysOfferAdapter todaysOfferAdapter;
    private PopularStoresAdapter popularStoresAdapter;
    private List<Datum> popularStoresArrayList = new ArrayList<>();
    private List<StyleStudioDatum> stylesList = new ArrayList<>();
    private StylesStudioAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)),
                (new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
        init1();

        return rootView;
    }

    private void init1() {

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
        recyclerViewPopularStore = rootView.findViewById(R.id.recyclerViewPopularStore);
        recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);

        todaysOfferAdapter = new TodaysOfferAdapter(stylesList, mContext, this);
        recyclerViewTopOffer.setLayoutManager(layout);
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();

        popularStoresAdapter = new PopularStoresAdapter(popularStoresArrayList, mContext, this);
        recyclerViewPopularStore.setLayoutManager(layoutB);
        recyclerViewPopularStore.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopularStore.setAdapter(popularStoresAdapter);
        popularStoreApi();
        styleStudioApi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewPopular:
                int pos = Integer.parseInt(v.getTag().toString());
                Datum categoryItem = popularStoresArrayList.get(pos);
                Intent intent = new Intent(mContext, StoreDetailActivity.class);
                intent.putExtra("id", categoryItem.getCId());
                intent.putExtra("name", categoryItem.getCompanyName());
                intent.putExtra("logo", categoryItem.getCompanyLogo());
                startActivity(intent);
                break;
            case R.id.cardView:
                /*int posA = Integer.parseInt(v.getTag().toString());
                StyleStudioDatum studioDatum = stylesList.get(posA);
                CategoryWiseDatum wiseDatum= new CategoryWiseDatum();
                wiseDatum.setCouponName();
                Intent intentA = new Intent(mContext, CouponDetailActivity.class);
                intentA.putExtra("coupon_detail", (Parcelable) studioDatum);
                startActivity(intentA);*/

                Alerts.show(mContext, "...in progress");
                break;
        }
    }

    private void popularStoreApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getStore(new Dialog(mContext), retrofitApiClient.getStore(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    StoreMainModel storeMainModel = (StoreMainModel) result.body();
                    assert storeMainModel != null;
                    popularStoresArrayList.clear();
                    popularStoresArrayList.addAll(storeMainModel.getData());
                    popularStoresAdapter.notifyDataSetChanged();
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

    private void styleStudioApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getStyleStudio(new Dialog(mContext), retrofitApiClient.styleStudio(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    StyleStudioMainModal mainModal = (StyleStudioMainModal) result.body();
                    if (mainModal == null)
                        return;
                    stylesList.addAll(mainModal.getData());
                    todaysOfferAdapter.notifyDataSetChanged();
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
