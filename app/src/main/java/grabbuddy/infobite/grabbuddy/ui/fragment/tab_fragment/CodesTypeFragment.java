package grabbuddy.infobite.grabbuddy.ui.fragment.tab_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.CategoryWiseCouponAdapter;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;
import grabbuddy.infobite.grabbuddy.ui.activities.CouponDetailActivity;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;

public class CodesTypeFragment extends BaseFragment implements FragmentChangeListener, View.OnClickListener {

    private View rootView;
    private RecyclerView recyclerViewCoupons;
    private List<CategoryWiseDatum> categoryWiseList = new ArrayList<>();
    private CategoryWiseCouponAdapter couponAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_all_coupons, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        if (getArguments() == null)
            return;
        categoryWiseList = getArguments().getParcelableArrayList("code");

        recyclerViewCoupons = rootView.findViewById(R.id.recyclerViewCoupons);
        couponAdapter = new CategoryWiseCouponAdapter(categoryWiseList, mContext, this);
        recyclerViewCoupons.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewCoupons.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCoupons.setAdapter(couponAdapter);
        couponAdapter.notifyDataSetChanged();

        if (categoryWiseList.size() > 0) {
            ((TextView) rootView.findViewById(R.id.tvEmpty)).setVisibility(View.GONE);
        } else {
            ((TextView) rootView.findViewById(R.id.tvEmpty)).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    @Override
    public void onClick(View v) {
        int pos = Integer.parseInt(v.getTag().toString());
        CategoryWiseDatum wiseDatum = categoryWiseList.get(pos);
        Intent intent = new Intent(mContext, CouponDetailActivity.class);
        intent.putExtra("coupon_detail", (Parcelable) wiseDatum);
        startActivity(intent);
    }
}
