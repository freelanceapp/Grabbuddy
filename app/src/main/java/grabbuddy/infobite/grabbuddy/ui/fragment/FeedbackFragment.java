package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.AllCategoryAdapter;
import grabbuddy.infobite.grabbuddy.adapter.FAQAdapter;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.FAQModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class FeedbackFragment extends BaseFragment implements FragmentChangeListener {

    private View rootView;
    private RecyclerView rvFaq;
    private FAQAdapter adapter;
    private ArrayList<Datum> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        rvFaq = (RecyclerView)rootView.findViewById(R.id.rv_faq);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        cd = new ConnectionDetector(mContext);

        allStoreApi();

        rvFaq.setLayoutManager((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)));
        rvFaq.setItemAnimator(new DefaultItemAnimator());
        adapter = new FAQAdapter(arrayList, mContext);
        rvFaq.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }


    private void allStoreApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getFaq(new Dialog(mContext), retrofitApiClient.getFaq(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    FAQModel storeMainModel = (FAQModel) result.body();
                    assert storeMainModel != null;
                    arrayList.clear();


                    arrayList.addAll(storeMainModel.getData());

                    adapter.notifyDataSetChanged();

                    /*if (offerMainModal.getMessage().equals("User is Not Verified")) {
                           // startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
                          //activity.finish();
                       }*/

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
