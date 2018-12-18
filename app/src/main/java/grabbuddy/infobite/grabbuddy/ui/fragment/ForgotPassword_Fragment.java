package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import grabbuddy.infobite.grabbuddy.CustomToast;
import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.Utils;
import grabbuddy.infobite.grabbuddy.modal.api_model.LoginModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.SignUpModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.LoginActivity;
import grabbuddy.infobite.grabbuddy.ui.activities.MainActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static grabbuddy.infobite.grabbuddy.constant.Constant.MY_PREFS_NAME;

public class ForgotPassword_Fragment extends BaseFragment implements OnClickListener {
	private static View view;
	private String getEmailId;
	private static EditText emailId;
	private static TextView submit, back;

	public ForgotPassword_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container,
				false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize the views
	private void initViews() {
		mContext = getActivity();
		retrofitRxClient = RetrofitService.getRxClient();
		retrofitApiClient = RetrofitService.getRetrofit();
		cd = new ConnectionDetector(mContext);
		emailId = (EditText) view.findViewById(R.id.registered_emailid);
		submit = (TextView) view.findViewById(R.id.forgot_button);
		back = (TextView) view.findViewById(R.id.backToLoginBtn);

		// Setting text selector over textviews
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			back.setTextColor(csl);
			submit.setTextColor(csl);

		} catch (Exception e) {
		}

	}

	// Set Listeners over buttons
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backToLoginBtn:

			// Replace Login Fragment on Back Presses
			new LoginActivity().replaceLoginFragment();
			break;

		case R.id.forgot_button:

			// Call Submit button task
			submitButtonTask();
			break;

		}

	}

	private void submitButtonTask() {
		getEmailId = emailId.getText().toString();

		// Pattern for email id validation
		Pattern p = Pattern.compile(Utils.regEx);

		// Match the pattern
		Matcher m = p.matcher(getEmailId);

		// First check if email id is not null else show error toast
		if (getEmailId.equals("") || getEmailId.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"Please enter your Email Id.");

			// Check if email id is valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

			// Else submit email id and fetch passwod or do your stuff
		else
			forgotApi();
	}


	private void forgotApi() {
		if (cd.isNetworkAvailable()) {

			RetrofitService.getForgotPassword(new Dialog(mContext), retrofitApiClient.getForgotPassword(getEmailId), new WebResponse() {
				@Override
				public void onResponseSuccess(Response<?> result) {
					SignUpModel storeMainModel = (SignUpModel) result.body();
					assert storeMainModel != null;

					if (storeMainModel.getMessage().equals("Please Enter Register Email Id"))
					{
						Alerts.show(mContext,storeMainModel.getMessage());
					}else {
						new LoginActivity().replaceLoginFragment();
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