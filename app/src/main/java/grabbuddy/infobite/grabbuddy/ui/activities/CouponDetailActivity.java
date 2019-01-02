package grabbuddy.infobite.grabbuddy.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import at.blogc.android.views.ExpandableTextView;
import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class CouponDetailActivity extends BaseActivity implements View.OnClickListener {

    private Boolean isCheck = true;

    private Context mContext;
    private CategoryWiseDatum wiseDatum;
    private String strOffer = "";
    private String type = "";
    private LinearLayout ll;
    private ImageView shareBtn;
    private ExpandableTextView tvDescription;
    private int lineCount = 0;
    private String strDesc = "";
    private RelativeLayout relativeShowMore;

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

        relativeShowMore = (RelativeLayout) findViewById(R.id.relativeShowMore);
        relativeShowMore.setOnClickListener(this);
        ((ImageView) findViewById(R.id.imgBack)).setOnClickListener(this);

        tvDescription = (ExpandableTextView) findViewById(R.id.tvDescription);
        wiseDatum = getIntent().getParcelableExtra("coupon_detail");
        type = getIntent().getStringExtra("type");

        ((LinearLayout) findViewById(R.id.linearShowStoreDetail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        strOffer = wiseDatum.getCouponOffer();
        ((TextView) findViewById(R.id.tvOffer)).setText(strOffer);
        ((TextView) findViewById(R.id.tvCouponCode)).setText(wiseDatum.getCouponCode());

        getCompanyName();

        String strLogo = "";
        if (AppPreference.getStringPreference(mContext, Constant.IMAGE_PREF) != null) {
            strLogo = AppPreference.getStringPreference(mContext, Constant.IMAGE_PREF);
        }

        Picasso.with(mContext)
                .load(Constant.IMAGE + strLogo)
                .placeholder(R.drawable.default_img)
                .into(((ImageView) findViewById(R.id.img)));

        if (wiseDatum.getCouponType().equalsIgnoreCase("Deals")) {
            ((Button) findViewById(R.id.btnActivateOffer)).setText("Activate Deals");
        } else if (wiseDatum.getCouponType().equalsIgnoreCase("Coupon")) {
            ((Button) findViewById(R.id.btnActivateOffer)).setText("Show Coupon");
        } else {
            ((Button) findViewById(R.id.btnActivateOffer)).setText("Show Offer");
        }

        ((Button) findViewById(R.id.btnActivateOffer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();

            }
        });

        shareBtn = (ImageView) findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Grab Buddy");
                    String sAux = "\nLet me recommend you this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        expandableTextview();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.relativeShowMore:
                if (tvDescription.isExpanded()) {
                    tvDescription.collapse();
                    ((TextView) findViewById(R.id.tvShowMore)).setText("Show More");
                } else {
                    tvDescription.expand();
                    ((TextView) findViewById(R.id.tvShowMore)).setText("Show Less");
                }
                break;
        }
    }

    public void showAlert() {
        // custom dialog
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_alertdialogbox);
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        if (wiseDatum.getCouponCode().equals("")) {
            text.setText("No Code is required! \n click below to Save Now");
        } else {
            text.setText("Code ''" + wiseDatum.getCouponCode() + "'' Copied \n paste this code at checkout");
        }
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String strUrl = wiseDatum.getCouponLink();
                if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                    strUrl = "http://" + strUrl;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                startActivity(browserIntent);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getCompanyName() {
        String strCompanyName = wiseDatum.getCouponLink();
        if (strCompanyName.contains(".")) {
            String strArray[] = strCompanyName.split("\\.");
            if (strArray.length > 1) {
                ((TextView) findViewById(R.id.tvName)).setText(strArray[1]);
                ((TextView) findViewById(R.id.tvSeeAll)).setText("See all " + strArray[1] + " " + "coupons");
            }
        } else {
            ((TextView) findViewById(R.id.tvName)).setText(strCompanyName);
            ((TextView) findViewById(R.id.tvSeeAll)).setText("See all " + strCompanyName + " " + "coupons");
        }
    }

    private void expandableTextview() {
        tvDescription.setAnimationDuration(750L);
        tvDescription.setInterpolator(new OvershootInterpolator());
        tvDescription.setExpandInterpolator(new OvershootInterpolator());
        tvDescription.setCollapseInterpolator(new OvershootInterpolator());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc()));
        }
        tvDescription.setText(strDesc);
        tvDescription.post(new Runnable() {
            @Override
            public void run() {
                lineCount = tvDescription.getLineCount();
                if (lineCount > 2) {
                    relativeShowMore.setVisibility(View.VISIBLE);
                } else {
                    relativeShowMore.setVisibility(View.GONE);
                }
            }
        });
    }
}
