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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.utils.AppPreference;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class CouponDetailActivity extends BaseActivity implements View.OnClickListener {

    Boolean isCheck = true;

    private Context mContext;
    CategoryWiseDatum wiseDatum;
    String strOffer = "";
    String type = "";
    LinearLayout ll;
    ImageView shareBtn;
    private TextView tvDescription;
    int MAX_LINES = 3;
    String strDesc = "";

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

        tvDescription = (TextView) findViewById(R.id.tvDescription);
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc(), Html.FROM_HTML_MODE_COMPACT));
            //((TextView) findViewById(R.id.tvDescription)).setText(strDesc);
        } else {
            strDesc = String.valueOf(Html.fromHtml(wiseDatum.getCouponDesc()));
            //((TextView) findViewById(R.id.tvDescription)).setText(strDesc);
        }
        tvDescription.setText(strDesc);
        tvDescription.post(new Runnable() {
            @Override
            public void run() {
                // Past the maximum number of lines we want to display.
                if (tvDescription.getLineCount() > MAX_LINES) {
                    int lastCharShown = tvDescription.getLayout().getLineVisibleEnd(MAX_LINES - 1);

                    tvDescription.setMaxLines(MAX_LINES);

                    String moreString = "Show more";
                    String suffix = " " + moreString;

                    // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
                    String actionDisplayText = strDesc.substring(0, lastCharShown - suffix.length() - 3) + "..." + suffix;

                    SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
                    int startIndex = actionDisplayText.indexOf(moreString);
                    truncatedSpannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.darkBlue)),
                            startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvDescription.setText(truncatedSpannableString);
                }
            }
        });

        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCheck) {
                    tvDescription.setMaxLines(15);
                    tvDescription.setText(strDesc);
                    isCheck = false;
                } else {
                    tvDescription.setMaxLines(3);
                    isCheck = true;
                }
            }
        });
    }
}
