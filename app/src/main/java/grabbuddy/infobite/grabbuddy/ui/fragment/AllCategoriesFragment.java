package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import grabbuddy.infobite.grabbuddy.adapter.AllCategoryAdapter;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryItemList;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.ui.activities.CategoryDetailActivity;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class AllCategoriesFragment extends BaseFragment implements FragmentChangeListener, View.OnClickListener {

    private View rootView;
    private RecyclerView recyclerViewAllCategory;
    private List<CategoryItemList> itemLists = new ArrayList<>();
    private AllCategoryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_all_categories, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootView;
    }

    private void init() {
        recyclerViewAllCategory = rootView.findViewById(R.id.recyclerViewAllCategory);
        recyclerViewAllCategory.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewAllCategory.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AllCategoryAdapter(itemLists, mContext, this);
        recyclerViewAllCategory.setAdapter(mAdapter);
        getAllCategory();
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llStore:
                int pos = Integer.parseInt(v.getTag().toString());
                CategoryItemList categoryItem = itemLists.get(pos);
                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("id", categoryItem.getCatId());
                intent.putExtra("name", categoryItem.getCatName());
                intent.putExtra("logo", categoryItem.getCatLogo());
                startActivity(intent);
                break;
        }
    }

    private void getAllCategory() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getAllCategory(new Dialog(mContext), retrofitApiClient.allCategory(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    CategoryMainModal mainModal = (CategoryMainModal) result.body();
                    itemLists.clear();
                    if (mainModal == null)
                        return;
                    itemLists.addAll(mainModal.getData());
                    if (itemLists.size() > 0) {
                        ((TextView) rootView.findViewById(R.id.tvEmpty)).setVisibility(View.GONE);
                    } else {
                        ((TextView) rootView.findViewById(R.id.tvEmpty)).setVisibility(View.VISIBLE);
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    //Alerts.show(mContext, error);
                    ((TextView) rootView.findViewById(R.id.tvEmpty)).setVisibility(View.VISIBLE);
                }
            });
        } else {
            cd.show(mContext);
        }
    }
}
