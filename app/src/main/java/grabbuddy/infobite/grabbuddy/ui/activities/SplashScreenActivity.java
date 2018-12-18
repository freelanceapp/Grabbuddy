package grabbuddy.infobite.grabbuddy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.ui.fragment.DealsFragment;

import static grabbuddy.infobite.grabbuddy.constant.Constant.MY_PREFS_NAME;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_EMAIL;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_ID;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_MOBILE;
import static grabbuddy.infobite.grabbuddy.constant.Constant.USER_NAME;

public class SplashScreenActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        mContext = SplashScreenActivity.this;

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        USER_ID = prefs.getString("user_id", "0");//"No name defined" is the default value.

        checkPermission();
    }

    protected void checkPermission() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (USER_ID.equals("0")) {
                    Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);

    }
}
