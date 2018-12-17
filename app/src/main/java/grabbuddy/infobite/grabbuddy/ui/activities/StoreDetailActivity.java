package grabbuddy.infobite.grabbuddy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class StoreDetailActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RecyclerView recyclerViewTopOffer;
    private List<Coupon> todaysOfferArrayList = new ArrayList<>();
    private TodaysOfferAdapter todaysOfferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        mContext = this;
        init();
    }

    private void init() {
        recyclerViewTopOffer = findViewById(R.id.recyclerViewTopOffer);

        String[] desc = {mContext.getString(R.string.desc_a), mContext.getString(R.string.desc_b),
                mContext.getString(R.string.desc_c), mContext.getString(R.string.desc_d),
                mContext.getString(R.string.desc_a),
                mContext.getString(R.string.desc_b)};

        for (int i = 0; i < desc.length; i++) {
            todaysOfferArrayList.add(new Coupon(desc[i], Constant.images[i], Constant.exclusiveImage[i]));
        }

        todaysOfferAdapter = new TodaysOfferAdapter(todaysOfferArrayList, mContext, this);
        recyclerViewTopOffer.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardView:
                startActivity(new Intent(mContext, CouponDetailActivity.class));
                break;
        }
    }
}
