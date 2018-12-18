package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
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
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class StylesStudioFragment extends BaseFragment {

    private View rootView;
    private RecyclerView recyclerViewStylesStudio;
    private List<StyleStudioDatum> stylesList = new ArrayList<>();
    private StylesStudioAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_styles_studio, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
        return rootView;
    }

    private void init() {
        recyclerViewStylesStudio = rootView.findViewById(R.id.recyclerViewStylesStudio);
        recyclerViewStylesStudio.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        recyclerViewStylesStudio.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new StylesStudioAdapter(stylesList, mContext);
        recyclerViewStylesStudio.setAdapter(mAdapter);
        styleStudioApi();
    }

    private void styleStudioApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getStyleStudio(new Dialog(mContext), retrofitApiClient.styleStudio(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    StyleStudioMainModal mainModal = (StyleStudioMainModal) result.body();
                    if (mainModal == null)
                        return;
                    stylesList.addAll(mainModal.getData());
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }
}
