package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.side_banner.SideBannerDatum;
import grabbuddy.infobite.grabbuddy.modal.side_banner.SideBannerModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class StealDealFragment extends BaseFragment implements FragmentChangeListener {

    private String strUrl = "";
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_steal_deal, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        ((ImageView) rootView.findViewById(R.id.imgBanner)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strUrl.isEmpty()) {
                    Alerts.show(mContext, "Website link empty!!!");
                } else {
                    if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                        strUrl = "http://" + strUrl;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                    startActivity(browserIntent);
                }
            }
        });
        getBannerImage();
    }

    private void getBannerImage() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getSideBanner(new Dialog(mContext), retrofitApiClient.sideBanner(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SideBannerModal bannerModal = (SideBannerModal) result.body();
                    if (bannerModal == null)
                        return;
                    if (bannerModal.getData().size() > 0) {
                        Picasso.with(mContext)
                                .load(Constant.IMAGE6 + bannerModal.getData().get(0).getOfferPicture())
                                .into(((ImageView) rootView.findViewById(R.id.imgBanner)));

                        strUrl = bannerModal.getData().get(0).getOfferLink();
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

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }
}
