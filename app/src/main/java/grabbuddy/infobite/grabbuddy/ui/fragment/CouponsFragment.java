package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.MarriagePagerAdapter;
import grabbuddy.infobite.grabbuddy.adapter.PopularStoresAdapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerDatum;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerModel;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.coupon_model.CouponDatum;
import grabbuddy.infobite.grabbuddy.modal.coupon_model.CouponModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitImagesService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.CouponDetailActivity;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.ui.activities.StoreDetailActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import grabbuddy.infobite.grabbuddy.utils.FragmentUtils;
import retrofit2.Response;

import static grabbuddy.infobite.grabbuddy.utils.FragmentUtils.HOME_FRAGMENT;
import static grabbuddy.infobite.grabbuddy.utils.FragmentUtils.HOME_ID;

public class CouponsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TodaysOfferAdapter todaysOfferAdapter;
    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private PopularStoresAdapter popularStoresAdapter;
    private List<Datum> popularStoresArrayList = new ArrayList<>();
    private List<CouponDatum> stylesList = new ArrayList<>();
    private TextView tvViewStore;
    private MarriagePagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        mContext = getActivity();
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientImages = RetrofitImagesService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        fragmentManager = (getActivity()).getSupportFragmentManager();

        init((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)),
                (new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        return rootView;
    }

    private void changeFragment(Fragment fragment, String strTag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.right_enter, R.anim.left_out);
        transaction.replace(R.id.frame_home_items, fragment, strTag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
        RecyclerView recyclerViewPopularStore = rootView.findViewById(R.id.recyclerViewPopularStore);
        RecyclerView recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);

        tvViewStore = rootView.findViewById(R.id.tvViewStore);
        tvViewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new TopStoresFragment(), Constant.TopStoresFragment);

            }
        });
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
        adapter = new MarriagePagerAdapter(mContext, successImagesList, this);
        imagesApi();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);
    }

    public void marriageSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
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
                int posA = Integer.parseInt(v.getTag().toString());
                CouponDatum studioDatum = stylesList.get(posA);
                CategoryWiseDatum wiseDatum = new CategoryWiseDatum();
                wiseDatum.setCouponOffer(studioDatum.getCouponOffer());
                wiseDatum.setCouponName(studioDatum.getCouponName());
                wiseDatum.setCouponType(studioDatum.getCouponType());
                wiseDatum.setCouponCode(studioDatum.getCouponCode());
                wiseDatum.setCouponDesc(studioDatum.getCouponDesc());
                wiseDatum.setCouponLink(studioDatum.getCouponLink());
                wiseDatum.setId(studioDatum.getId());
                wiseDatum.setDateTime(studioDatum.getCompany_logo());
                wiseDatum.setCompanyId(studioDatum.getCompanyId());
                wiseDatum.setStatus(studioDatum.getCompany_name());
                AppPreference.setStringPreference(mContext, Constant.IMAGE_PREF, studioDatum.getCompany_logo());
                Intent intentA = new Intent(mContext, CouponDetailActivity.class);
                intentA.putExtra("coupon_detail", (Parcelable) wiseDatum);
                intentA.putExtra("type","CouponFragment");
                startActivity(intentA);
                break;
            case R.id.imageView:
                int posB = Integer.parseInt(v.getTag().toString());
                BannerDatum bannerDatum = successImagesList.get(posB);
                String strUrl = bannerDatum.getOfferLink();
                if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                    strUrl = "http://" + strUrl;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                startActivity(browserIntent);
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

                    for (int i = 0; i < storeMainModel.getData().size(); i++) {
                        if (storeMainModel.getData().get(i).getTopStoreStatus().equals("Y")) {
                            Datum datum = new Datum();
                            datum.setCId(storeMainModel.getData().get(i).getCId());
                            datum.setCompanyLogo(storeMainModel.getData().get(i).getCompanyLogo());
                            datum.setCompanyName(storeMainModel.getData().get(i).getCompanyName());
                            datum.setCompanyUrl(storeMainModel.getData().get(i).getCompanyUrl());
                            datum.setDateTime(storeMainModel.getData().get(i).getDateTime());
                            datum.setStatus(storeMainModel.getData().get(i).getStatus());
                            datum.setTopStoreStatus(storeMainModel.getData().get(i).getTopStoreStatus());

                            popularStoresArrayList.add(datum);
                        }
                    }
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
