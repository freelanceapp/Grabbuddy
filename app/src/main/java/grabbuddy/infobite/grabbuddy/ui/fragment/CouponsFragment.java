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

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.MarriagePagerAdapter;
import grabbuddy.infobite.grabbuddy.adapter.PopularStoresAdapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerDatum;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerModel;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.coupon_model.CouponDatum;
import grabbuddy.infobite.grabbuddy.modal.coupon_model.CouponModel;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioMainModal;
import grabbuddy.infobite.grabbuddy.modal.success_modal.MarriageSuccessModal;
import grabbuddy.infobite.grabbuddy.modal.success_modal.SuccessImage;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitImagesService;
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
    private TodaysOfferAdapter todaysOfferAdapter;
    private Handler handler, imageHandler;
    private Runnable runnable, imageRunnable;
    private ViewPager mViewPager, pagerSuccess;
    private PopularStoresAdapter popularStoresAdapter;
    private List<Datum> popularStoresArrayList = new ArrayList<>();
    private List<CouponDatum> stylesList = new ArrayList<>();


    private MarriagePagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientImages = RetrofitImagesService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        init((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)),
                (new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        return rootView;
    }

    private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
        RecyclerView recyclerViewPopularStore = rootView.findViewById(R.id.recyclerViewPopularStore);
        RecyclerView recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);

        todaysOfferAdapter = new TodaysOfferAdapter(stylesList, mContext, this);
        recyclerViewTopOffer.setLayoutManager(layout);
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();

        popularStoresAdapter = new PopularStoresAdapter(popularStoresArrayList, mContext, this);
        recyclerViewPopularStore.setLayoutManager(layoutB);
        recyclerViewPopularStore.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopularStore.setAdapter(popularStoresAdapter);

        pagerSuccess = (ViewPager) rootView.findViewById(R.id.pagerSuccess);

        popularStoreApi();
        initImages();
        getCoupon1();
    }

    private void initImages() {
        imagesApi();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 5000);
    }

    public void marriageSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 5000);
        }
    }

  /*  private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
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
        getCoupon1();
        //styleStudioApi();
    }*/

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
                int posA = Integer.parseInt(v.getTag().toString());
                CouponDatum studioDatum = stylesList.get(posA);
                CategoryWiseDatum wiseDatum = new CategoryWiseDatum();
                wiseDatum.setCouponOffer("15");
                wiseDatum.setCouponName(studioDatum.getCouponName());
                wiseDatum.setCouponCode("THE23WJKD");
                wiseDatum.setCouponDesc("");
                wiseDatum.setCouponLink(studioDatum.getCouponLink());
                Intent intentA = new Intent(mContext, CouponDetailActivity.class);
                intentA.putExtra("coupon_detail", (Parcelable) wiseDatum);
                startActivity(intentA);
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



    private void imagesApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getBanner(new Dialog(mContext), retrofitApiClient.getBanner(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    BannerModel imagesModal = (BannerModel) result.body();
                    successImagesList.clear();
                    if (imagesModal == null)
                        return;
                    successImagesList.addAll(imagesModal.getData());

                    adapter = new MarriagePagerAdapter(mContext, successImagesList);
                    pagerSuccess.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void getCoupon1() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getCoupon(new Dialog(mContext), retrofitApiClient.getCoupon(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CouponModel mainModal = (CouponModel) result.body();
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
