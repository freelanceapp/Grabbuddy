package grabbuddy.infobite.grabbuddy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class CouponDetailActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        mContext = CouponDetailActivity.this;

        checkPermission();
    }

    protected void checkPermission() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CouponDetailActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}
