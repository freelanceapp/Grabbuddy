package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.api_model.LoginModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.SignUpModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitImagesService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static grabbuddy.infobite.grabbuddy.constant.Constant.MY_PREFS_NAME;

public class ShareAppFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private EditText tvEmail;
    private Button btnSubmit;
    String emailId = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_share_app, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientImages = RetrofitImagesService.getRetrofit();
        tvEmail = (EditText) rootView.findViewById(R.id.tvEmail);

        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailId = tvEmail.getText().toString();
                if (emailId.equals(""))
                {
                    Alerts.show(mContext,"Enter Email ID");
                }else {
                    subscribeApi();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    private void subscribeApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getSubscribe(new Dialog(mContext), retrofitApiClient.getSubscribe(emailId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SignUpModel sign = (SignUpModel) result.body();
                    assert sign != null;
                        Alerts.show(mContext, sign.getMessage());
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
