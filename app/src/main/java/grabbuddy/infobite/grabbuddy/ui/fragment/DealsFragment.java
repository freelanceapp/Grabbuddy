package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bakerj.infinitecards.AnimationTransformer;
import com.bakerj.infinitecards.CardItem;
import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.ZIndexTransformer;
import com.bakerj.infinitecards.transformer.DefaultCommonTransformer;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.CardViewAdapter;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealDataList;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class DealsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private InfiniteCardView mCardView;
    private CardViewAdapter mAdapter2;
    private List<TodayDealDataList> dealDataLists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_deals, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootView;
    }

    private void init() {
        mCardView = rootView.findViewById(R.id.viewInfinite);

        if (getArguments() == null)
            return;
        dealDataLists = getArguments().getParcelableArrayList("deals_list");
        setCardItems(dealDataLists);
    }

    private void initButton() {
        rootView.findViewById(R.id.pre).setOnClickListener(this);
        rootView.findViewById(R.id.close).setOnClickListener(this);
        rootView.findViewById(R.id.next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre:
                setStyle1();
                mCardView.bringCardToFront(mAdapter2.getCount() - 1);
                break;
            case R.id.next:
                setStyle3();
                mCardView.bringCardToFront(1);
                break;
            case R.id.cardViewDeal:
                int pos = Integer.parseInt(v.getTag().toString());
                TodayDealDataList dealData = dealDataLists.get(pos);
                String strUrl = dealData.getPrdctLink();
                if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                    strUrl = "http://" + strUrl;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                startActivity(browserIntent);
                break;
        }
    }

    private void setStyle1() {
        mCardView.setClickable(true);
        mCardView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT);
        mCardView.setAnimInterpolator(new LinearInterpolator());
        mCardView.setTransformerToFront(new DefaultTransformerToFront());
        mCardView.setTransformerToBack(new DefaultTransformerToBack());
        mCardView.setZIndexTransformerToBack(new DefaultZIndexTransformerCommon());
    }

    private void setStyle3() {
        mCardView.setClickable(false);
        mCardView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT_TO_LAST);
        mCardView.setAnimInterpolator(new OvershootInterpolator(-8));
        mCardView.setTransformerToFront(new DefaultCommonTransformer());
        mCardView.setTransformerToBack(new AnimationTransformer() {
            @Override
            public void transformAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                view.setScaleX(scale);
                view.setScaleY(scale);
                if (fraction < 0.5) {
                    view.setTranslationX(cardWidth * fraction * 1.5f);
                    view.setRotationY(-45 * fraction);
                } else {
                    view.setTranslationX(cardWidth * 1.5f * (1f - fraction));
                    view.setRotationY(-45 * (1 - fraction));
                }
            }

            @Override
            public void transformInterpolatedAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                int positionCount = fromPosition - toPosition;
                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
                view.setTranslationY(-cardHeight * (0.8f - scale) * 0.5f - cardWidth * (0.02f *
                        fromPosition - 0.02f * fraction * positionCount));
            }
        });
        mCardView.setZIndexTransformerToBack(new ZIndexTransformer() {
            @Override
            public void transformAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
                if (fraction < 0.5f) {
                    card.zIndex = 1f + 0.01f * fromPosition;
                } else {
                    card.zIndex = 1f + 0.01f * toPosition;
                }
            }

            @Override
            public void transformInterpolatedAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {

            }
        });
    }

    private void setCardItems(List<TodayDealDataList> dataLists) {
        mAdapter2 = new CardViewAdapter(mContext, dataLists, this);
        mCardView.setAdapter(mAdapter2);
        mCardView.setCardSizeRatio(1.0f);
        mCardView.setCardAnimationListener(new InfiniteCardView.CardAnimationListener() {
            @Override
            public void onAnimationStart() {
                //Alerts.show(mContext, "Animation Start");
            }

            @Override
            public void onAnimationEnd() {
                //Alerts.show(mContext, "Animation End");
            }
        });
        initButton();
    }
}
