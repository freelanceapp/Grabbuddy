package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.AllCategoryAdapter;
import grabbuddy.infobite.grabbuddy.adapter.StylesStudioAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;

public class AllCategoriesFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private RecyclerView recyclerViewAllCategory;
    private List<Coupon> stylesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_all_categories, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {

        for (int i = 0; i < Constant.images.length; i++) {
            stylesList.add(new Coupon(Constant.images[i]));
        }

        recyclerViewAllCategory = rootView.findViewById(R.id.recyclerViewAllCategory);
        recyclerViewAllCategory.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewAllCategory.setItemAnimator(new DefaultItemAnimator());
        AllCategoryAdapter mAdapter = new AllCategoryAdapter(stylesList, mContext);
        recyclerViewAllCategory.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }
}
