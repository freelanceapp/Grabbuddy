package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import grabbuddy.infobite.grabbuddy.adapter.SlideShowPagerAdapter;
import grabbuddy.infobite.grabbuddy.adapter.SlidingImage_Adapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.UserImagesDatum;
import grabbuddy.infobite.grabbuddy.modal.UserImagesModal;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerModel;
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
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, R.drawable.img_d};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView recyclerViewPopularStore, recyclerViewTopOffer;
    private TodaysOfferAdapter todaysOfferAdapter;
    private Handler handler, imageHandler;
    private Runnable runnable, imageRunnable;
    private ViewPager mViewPager, pagerSuccess;
    private List<grabbuddy.infobite.grabbuddy.modal.banner_model.Datum> imagesDatumList = new ArrayList<>();

    private PopularStoresAdapter popularStoresAdapter;
    private List<Coupon> todaysOfferArrayList = new ArrayList<>();
    private List<Datum> popularStoresArrayList = new ArrayList<>();
    private SlideShowPagerAdapter mSlideShowPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)),
                (new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));

        sliderHandler();
        userImagesApi();
         mSlideShowPagerAdapter = new SlideShowPagerAdapter(mContext, imagesDatumList);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mSlideShowPagerAdapter);
        return rootView;
    }

    /*
     *  User Image Slider Handler
     * */
    private void sliderHandler() {
        handler = new Handler();
        imageHandler = new Handler();

        // User images slider
        runnable = new Runnable() {
            @Override
            public void run() {
                slideGalleryPic();
            }
        };
        handler.postDelayed(runnable, 5000);

        // Successful marriage user slider
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 5000);
    }


    public void slideGalleryPic() {
        if (mViewPager == null)
            return;
        int currentPos = mViewPager.getCurrentItem();
        currentPos++;
        if (currentPos != imagesDatumList.size()) {
            mViewPager.setCurrentItem(currentPos);
            handler.postDelayed(runnable, 5000);
        }
    }

    public void marriageSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        /*if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 5000);
        }*/
    }




    private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
        recyclerViewPopularStore = rootView.findViewById(R.id.recyclerViewPopularStore);
        recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);

        String[] desc = {mContext.getString(R.string.desc_a), mContext.getString(R.string.desc_b),
                mContext.getString(R.string.desc_c), mContext.getString(R.string.desc_d),
                mContext.getString(R.string.desc_a),
                mContext.getString(R.string.desc_b)};

        for (int i = 0; i < desc.length; i++) {
            todaysOfferArrayList.add(new Coupon(desc[i], Constant.images[i], Constant.exclusiveImage[i]));
        }

        todaysOfferAdapter = new TodaysOfferAdapter(todaysOfferArrayList, mContext, this);
        recyclerViewTopOffer.setLayoutManager(layout);
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();

        popularStoresAdapter = new PopularStoresAdapter(popularStoresArrayList, mContext, this);
        recyclerViewPopularStore.setLayoutManager(layoutB);
        recyclerViewPopularStore.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopularStore.setAdapter(popularStoresAdapter);
        popularStoreApi();
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
                startActivity(new Intent(mContext, CouponDetailActivity.class));
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
    /* User images list */
    private void userImagesApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getBanner(new Dialog(mContext), retrofitApiClient.getBanner(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    Response<BannerModel> response = (Response<BannerModel>) result;
                    BannerModel imagesModal = response.body();
                    imagesDatumList.clear();
                        imagesDatumList.addAll(imagesModal.getData());
                        //AppAlerts.show(mContext, imagesModal.getMsg());



                    /*pagerSuccess = (ViewPager) rootView.findViewById(R.id.pagerSuccess);
                    pagerSuccess.setAdapter(mSlideShowPagerAdapter);*/
                    mSlideShowPagerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }

    }



}
