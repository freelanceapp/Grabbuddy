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
import grabbuddy.infobite.grabbuddy.adapter.StylesStudioAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;

public class StylesStudioFragment extends BaseFragment {

    private View rootView;
    private RecyclerView recyclerViewStylesStudio;
    private List<Coupon> stylesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_styles_studio, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {

        for (int i = 0; i < Constant.images.length; i++) {
            stylesList.add(new Coupon(Constant.images[i]));
        }

        recyclerViewStylesStudio = rootView.findViewById(R.id.recyclerViewStylesStudio);
        recyclerViewStylesStudio.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewStylesStudio.setItemAnimator(new DefaultItemAnimator());
        StylesStudioAdapter mAdapter = new StylesStudioAdapter(stylesList, mContext);
        recyclerViewStylesStudio.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
