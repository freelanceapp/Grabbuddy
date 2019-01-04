package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.FAQModel;
import grabbuddy.infobite.grabbuddy.modal.term_responce.TermModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitImagesService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class TermConditionFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private TextView tvDetail,tvDate;
    private String full_term="";
    private String full_date="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_termsandconditions, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientImages = RetrofitImagesService.getRetrofit();
        tvDate = (TextView)rootView.findViewById(R.id.tvDate);
        tvDetail = (TextView)rootView.findViewById(R.id.tvDetail);
        getTerm();
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    private void getTerm() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getTerm(new Dialog(mContext), retrofitApiClient.getTerm(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    TermModel storeMainModel = (TermModel) result.body();
                    assert storeMainModel != null;
                    for (int i = 0 ; i < storeMainModel.getData().size() ; i++) {
                        Log.e("Store Name", ".." + storeMainModel.getData().get(i).getTermsAndConditionsDesc());
                        //policy = privacyModel.getData().get(i).getPrivacyPolicyDesc();
                        full_term =  full_term +"\n " +storeMainModel.getData().get(i).getTermsAndConditionsDesc();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvDetail.setText(Html.fromHtml(full_term, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        tvDetail.setText(Html.fromHtml(full_term));
                    }

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
