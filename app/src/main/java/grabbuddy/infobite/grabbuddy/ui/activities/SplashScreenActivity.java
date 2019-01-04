package grabbuddy.infobite.grabbuddy.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.modal.tokan_responce.TokenModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

import static grabbuddy.infobite.grabbuddy.constant.Constant.MY_PREFS_NAME;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_ID;

public class SplashScreenActivity extends BaseActivity {
    private Context mContext;
    public static String android_id = "";
    public static String refreshedToken = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Grubbuddy", "Refreshed token: " + refreshedToken);
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("Grubbuddy", "Device : " + android_id);
        mContext = SplashScreenActivity.this;
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        USER_ID = prefs.getString("user_id", "0");//"No name defined" is the default value.

        checkPermission();
    }

    protected void checkPermission() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (USER_ID.equals("0")) {
                    if (!AppPreference.getBooleanPreference(mContext, "Welcome")) {
                        Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    i.putExtra("isLogin", "splash");
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);

    }




}
