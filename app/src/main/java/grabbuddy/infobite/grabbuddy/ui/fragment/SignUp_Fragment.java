package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import grabbuddy.infobite.grabbuddy.CustomToast;
import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.Utils;
import grabbuddy.infobite.grabbuddy.ui.activities.LoginActivity;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText firstName, lastName, emailId, mobileNumber, location,
			password, confirmPassword;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;
	private ProgressBar progressBar;
	String getFirstName, getLastName, getUserName, getEmailId, getPassword, getMobileNumber, getConfirmPassword;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		progressBar = (ProgressBar) view.findViewById(R.id.progress);

		firstName = (EditText) view.findViewById(R.id.firstName);
		lastName = (EditText) view.findViewById(R.id.lastName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		// Setting text selector over textviews
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.signUpBtn:

				// Call checkValidation method
				checkValidation();
				break;

			case R.id.already_user:

				// Replace login fragment
				new LoginActivity().replaceLoginFragment();
				break;
		}

	}

	// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		getFirstName = firstName.getText().toString();
		getLastName = lastName.getText().toString();
		getEmailId = emailId.getText().toString();
		getMobileNumber = mobileNumber.getText().toString();
		getPassword = password.getText().toString();
		getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFirstName.equals("") || getFirstName.length() == 0
				|| getLastName.equals("") || getLastName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"All fields are required.");

			// Check if email id valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

			// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			new CustomToast().Show_Toast(getActivity(), view,
					"Both password doesn't match.");

			// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked())
			new CustomToast().Show_Toast(getActivity(), view,
					"Please select Terms and Conditions.");

			// Else do signup or do your stuff
		else {
			Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT).show();

		}
	}

}