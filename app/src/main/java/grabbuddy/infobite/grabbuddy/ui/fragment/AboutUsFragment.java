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
import grabbuddy.infobite.grabbuddy.modal.api_model.about_model.AboutModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model.PrivacyModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class AboutUsFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private TextView tv_about;
    private String about = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_about, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);
        tv_about = (TextView)rootView.findViewById(R.id.tv_about);
        aboutUsApi();
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    private void aboutUsApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getAbout(new Dialog(mContext), retrofitApiClient.getAbout(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    AboutModel privacyModel = (AboutModel) result.body();
                    assert privacyModel != null;

                    for (int i = 0 ; i < privacyModel.getData().size() ; i++) {
                        Log.e("Store Name", ".." + privacyModel.getData().get(i).getAboutDesc());
                        //policy = privacyModel.getData().get(i).getPrivacyPolicyDesc();
                        about =  about +"\n " +privacyModel.getData().get(i).getAboutDesc();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tv_about.setText(Html.fromHtml(about, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        tv_about.setText(Html.fromHtml(about));
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
