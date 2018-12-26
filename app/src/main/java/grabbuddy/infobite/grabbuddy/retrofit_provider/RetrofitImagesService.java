package grabbuddy.infobite.grabbuddy.retrofit_provider;

import android.app.Dialog;

import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.success_modal.MarriageSuccessModal;
import grabbuddy.infobite.grabbuddy.utils.AppProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImagesService {

    public static RetrofitApiClient client;

    public RetrofitImagesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kirarkshatriya.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRxClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        return retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRetrofit() {
        if (client == null)
            new RetrofitImagesService();

        return client;
    }
}