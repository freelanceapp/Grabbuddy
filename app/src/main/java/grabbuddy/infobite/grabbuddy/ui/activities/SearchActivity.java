package grabbuddy.infobite.grabbuddy.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.CustomerListAdapter;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.search_modal_data.SearchStoreDatum;
import grabbuddy.infobite.grabbuddy.modal.search_modal_data.SearchStoreMainModal;
import grabbuddy.infobite.grabbuddy.retrofit_provider.RetrofitService;
import grabbuddy.infobite.grabbuddy.retrofit_provider.WebResponse;
import grabbuddy.infobite.grabbuddy.utils.Alerts;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;
import grabbuddy.infobite.grabbuddy.utils.ConnectionDetector;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    private CustomerListAdapter customerListAdapter;
    List<SearchStoreDatum> offersModelArrayList = new ArrayList<>();
    String companyId = "";
    private Datum datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        allStoreApi();
        init();
    }

    private void init() {

        customerListAdapter = new CustomerListAdapter(mContext, R.layout.row_top_stores, offersModelArrayList);
        ListView listViewCustomer = (ListView) findViewById(R.id.listViewCustomer);
        listViewCustomer.setAdapter(customerListAdapter);

        ((EditText) findViewById(R.id.edtSearchCustomer))
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence cs, int start, int before, int count) {
                        customerListAdapter.getFilter().filter(cs);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


        listViewCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                companyId = offersModelArrayList.get(position).getCId();
                // ((TextView)findViewById(R.id.tvSelectCustomer)).setText(location);

                Intent intent = new Intent(mContext, StoreDetailActivity.class);
                intent.putExtra("id", offersModelArrayList.get(position).getCId());
                intent.putExtra("name", offersModelArrayList.get(position).getCompanyName());
                intent.putExtra("logo", offersModelArrayList.get(position).getCompanyLogo());
                startActivity(intent);
                finish();
            }
        });
    }


    private void allStoreApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getSearchStore(new Dialog(mContext), retrofitApiClient.getSearchStore(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SearchStoreMainModal storeMainModel = (SearchStoreMainModal) result.body();
                    assert storeMainModel != null;
                   offersModelArrayList.clear();

                    //Log.e("store",".."+storeMainModel.getData().get(1).getCompanyName());
/*

                    for (int i = 0 ; i < storeMainModel.getData().size() ; i++)
                    {
                        SearchStoreDatum searchStoreDatum = new SearchStoreDatum();
                        searchStoreDatum.setCId(storeMainModel.getData().get(i).getCId());
                        searchStoreDatum.setCompanyLogo(storeMainModel.getData().get(i).getCompanyLogo());
                        searchStoreDatum.setCompanyName(storeMainModel.getData().get(i).getCompanyName());
                        searchStoreDatum.setCompanyUrl(storeMainModel.getData().get(i).getCompanyUrl());
                        searchStoreDatum.setDateTime(storeMainModel.getData().get(i).getDateTime());

                        Log.e("Store",".."+storeMainModel.getData().size());
                        offersModelArrayList.add(searchStoreDatum);
                    }
*/

                    offersModelArrayList.addAll(storeMainModel.getData());

                    customerListAdapter.notifyDataSetChanged();

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
