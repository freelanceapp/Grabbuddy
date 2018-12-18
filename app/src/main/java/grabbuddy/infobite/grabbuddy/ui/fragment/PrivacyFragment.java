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
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model.PrivacyModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class PrivacyFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private TextView tv_policy;
    private String policy;
    private String full_policy="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_private_policy, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        tv_policy = (TextView)rootView.findViewById(R.id.tv_policy);

        policyApi();
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    private void policyApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getPolicy(new Dialog(mContext), retrofitApiClient.getPolicy(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PrivacyModel privacyModel = (PrivacyModel) result.body();
                    assert privacyModel != null;

                    for (int i = 0 ; i < privacyModel.getData().size() ; i++) {
                        Log.e("Store Name", ".." + privacyModel.getData().get(i).getPrivacyPolicyDesc());
                        //policy = privacyModel.getData().get(i).getPrivacyPolicyDesc();
                        full_policy =  full_policy +"\n " +privacyModel.getData().get(i).getPrivacyPolicyDesc();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tv_policy.setText(Html.fromHtml(full_policy, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        tv_policy.setText(Html.fromHtml(full_policy));
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

}
