package grabbuddy.infobite.grabbuddy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class CouponDetailActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        mContext = this;
        init();
    }

    private void init() {
        if (getIntent() == null)
            return;
        ((ImageView) findViewById(R.id.imgBack)).setOnClickListener(this);

        final CategoryWiseDatum wiseDatum = getIntent().getParcelableExtra("coupon_detail");
        StyleStudioDatum studioDatum = getIntent().getParcelableExtra("coupon_detail");

        String strOffer = wiseDatum.getCouponOffer();
        ((TextView) findViewById(R.id.tvName)).setText(wiseDatum.getCouponName());
        ((TextView) findViewById(R.id.tvOffer)).setText(strOffer);
        ((TextView) findViewById(R.id.tvCouponCode)).setText(wiseDatum.getCouponCode());

        String strLogo = "";
        if (AppPreference.getStringPreference(mContext, Constant.IMAGE_PREF) != null) {
            strLogo = AppPreference.getStringPreference(mContext, Constant.IMAGE_PREF);
        }

        Picasso.with(mContext)
                .load(Constant.IMAGE + strLogo)
                .placeholder(R.drawable.default_img)
                .into(((ImageView) findViewById(R.id.img)));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc(), Html.FROM_HTML_MODE_COMPACT));
            ((TextView) findViewById(R.id.tvDescription)).setText(strDesc);
        } else {
            String strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc()));
            ((TextView) findViewById(R.id.tvDescription)).setText(strDesc);
        }

        ((Button) findViewById(R.id.btnActivateOffer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUrl = wiseDatum.getCouponLink();
                if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                    strUrl = "http://" + strUrl;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
