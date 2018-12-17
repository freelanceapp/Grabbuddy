package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.OffersAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitApiClient;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;
import retrofit2.Response;

public class TopStoresFragment extends BaseFragment implements FragmentChangeListener,  View.OnClickListener {

    private View rootView;
    RecyclerView recyclerView;
    OffersAdapter offersAdapter;
    List<Datum> offersModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_top_stores, container, false);
        mContext = getActivity();
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        init((new GridLayoutManager(mContext, 2)));
        return rootView;
    }

    private void init(RecyclerView.LayoutManager layout) {

        ((TextView) rootView.findViewById(R.id.tvAllStores)).setOnClickListener(this);

       /* for (int i = 0; i < Constant.nameArray.length; i++) {
            offersModelArrayList.add(new Coupon(Constant.nameArray[i], Constant.drawableArray[i]));
        }*/

        allStoreApi();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        offersAdapter = new OffersAdapter(offersModelArrayList, mContext);
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(offersAdapter);
        offersAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(mContext, ShareAppFragment.class));
    }

    @Override
    public void onFragmentVisible(String fragmentTag) {

    }


    private void allStoreApi() {
        //if (cd.isNetworkAvailable()) {

            RetrofitService.getStore(new Dialog(mContext), retrofitApiClient.getStore(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    StoreMainModel storeMainModel = (StoreMainModel) result.body();
                    assert storeMainModel != null;
                    offersModelArrayList.clear();


                        offersModelArrayList.addAll(storeMainModel.getData());

                        offersAdapter.notifyDataSetChanged();

                        for (int i = 0 ; i < storeMainModel.getData().size() ; i++) {
                            Log.e("Store Name", ".." + storeMainModel.getData().get(i).getCompanyName());
                        }
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

       /* } else {
            cd.show(mContext);
        }*/
    }


}
