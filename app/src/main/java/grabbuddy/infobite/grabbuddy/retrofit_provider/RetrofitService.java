package grabbuddy.infobite.grabbuddy.retrofit_provider;

import android.app.Dialog;

import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.api_model.LoginModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.SignUpModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryMainModal;
import grabbuddy.infobite.grabbuddy.modal.api_model.about_model.AboutModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.FAQModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model.PrivacyModel;
import grabbuddy.infobite.grabbuddy.modal.banner_model.BannerModel;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseMainModal;
import grabbuddy.infobite.grabbuddy.modal.coupon_model.CouponModel;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioMainModal;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealMainModal;
import grabbuddy.infobite.grabbuddy.utils.AppProgressDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static RetrofitApiClient client;

    public RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
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
            new RetrofitService();

        return client;
    }

    public static void getForgotPassword(final Dialog dialog, final Call<SignUpModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    // All Store
    public static void getStore(final Dialog dialog, final Call<StoreMainModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<StoreMainModel>() {
            @Override
            public void onResponse(Call<StoreMainModel> call, Response<StoreMainModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<StoreMainModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getAllCategory(final Dialog dialog, final Call<CategoryMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<CategoryMainModal>() {
            @Override
            public void onResponse(Call<CategoryMainModal> call, Response<CategoryMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<CategoryMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getPolicy(final Dialog dialog, final Call<PrivacyModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<PrivacyModel>() {
            @Override
            public void onResponse(Call<PrivacyModel> call, Response<PrivacyModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<PrivacyModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getAbout(final Dialog dialog, final Call<AboutModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<AboutModel>() {
            @Override
            public void onResponse(Call<AboutModel> call, Response<AboutModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<AboutModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getFaq(final Dialog dialog, final Call<FAQModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<FAQModel>() {
            @Override
            public void onResponse(Call<FAQModel> call, Response<FAQModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<FAQModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getSignUp(final Dialog dialog, final Call<SignUpModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getLogin(final Dialog dialog, final Call<LoginModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getCategoryWiseData(final Dialog dialog, final Call<CategoryWiseMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<CategoryWiseMainModal>() {
            @Override
            public void onResponse(Call<CategoryWiseMainModal> call, Response<CategoryWiseMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<CategoryWiseMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getTodayDeal(final Dialog dialog, final Call<TodayDealMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<TodayDealMainModal>() {
            @Override
            public void onResponse(Call<TodayDealMainModal> call, Response<TodayDealMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<TodayDealMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getBanner(final Dialog dialog, final Call<BannerModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getStyleStudio(final Dialog dialog, final Call<StyleStudioMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<StyleStudioMainModal>() {
            @Override
            public void onResponse(Call<StyleStudioMainModal> call, Response<StyleStudioMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<StyleStudioMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }


    public static void getCoupon(final Dialog dialog, final Call<CouponModel> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<CouponModel>() {
            @Override
            public void onResponse(Call<CouponModel> call, Response<CouponModel> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<CouponModel> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
}