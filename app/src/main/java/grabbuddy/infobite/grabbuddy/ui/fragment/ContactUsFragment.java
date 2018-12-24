package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

public class ContactUsFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private EditText tvFullName,tvEmailId,tvContact,tvBusness,tvMessage;
    private Button btnSubmit1;
    String fullName,emailId,busness,contact,message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientImages = RetrofitImagesService.getRetrofit();
        tvFullName = (EditText)rootView.findViewById(R.id.tvFullName);
        tvEmailId = (EditText)rootView.findViewById(R.id.tvEmailId);
        tvBusness = (EditText)rootView.findViewById(R.id.tvBudiness);
        tvContact = (EditText)rootView.findViewById(R.id.tvContact);
        tvMessage = (EditText)rootView.findViewById(R.id.tvMessage);
        btnSubmit1 = (Button)rootView.findViewById(R.id.btnSubmit1);

        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName = tvFullName.getText().toString();
                emailId = tvEmailId.getText().toString();
                busness = tvBusness.getText().toString();
                contact = tvContact.getText().toString();
                message = tvMessage.getText().toString();
                if (fullName.equals(""))
                {
                    Alerts.show(mContext,"Please Fill All Fields");
                }else if (emailId.equals(""))
                {
                    Alerts.show(mContext,"Please Fill All Fields");

                }else if (busness.equals(""))
                {
                    Alerts.show(mContext,"Please Fill All Fields");

                }else if (contact.equals(""))
                {
                    Alerts.show(mContext,"Please Fill All Fields");

                }else if (message.equals(""))
                {
                    Alerts.show(mContext,"Please Fill All Fields");

                }
                else {
                    contactApi();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    private void contactApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getContact(new Dialog(mContext), retrofitApiClient.getContact(fullName,emailId,busness,contact,message), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SignUpModel storeMainModel = (SignUpModel) result.body();
                    assert storeMainModel != null;

                        Alerts.show(mContext, storeMainModel.getMessage());

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
