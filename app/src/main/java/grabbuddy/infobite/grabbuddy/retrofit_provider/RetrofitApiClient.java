package grabbuddy.infobite.grabbuddy.retrofit_provider;

import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.api_model.LoginModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.SignUpModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryMainModal;
import grabbuddy.infobite.grabbuddy.modal.api_model.about_model.AboutModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.FAQModel;
import grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model.PrivacyModel;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseMainModal;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitApiClient {

    @GET(Constant.ALL_CATEGORY)
    Call<CategoryMainModal> allCategory();

    @GET(Constant.CATEGORY_WISE)
    Call<CategoryWiseMainModal> categoryWiseData(@Query("category_id") String category_id);

    @GET(Constant.COMPANY_WISE)
    Call<CategoryWiseMainModal> companyWiseData(@Query("company_id") String company_id);

    @FormUrlEncoded
    @POST(Constant.VERIFICATION)
    Call<ResponseBody> userVerification(@Field("mobile_number") String mobile_number,
                                        @Field("otp_number") String otp_number);

    @FormUrlEncoded
    @POST(Constant.VENDOR_DETAIL)
    Call<ResponseBody> vendorDetail(@Field("vendor_id") String vendor_id);

    @FormUrlEncoded
    @POST(Constant.VENDOR_LIST)
    Call<ResponseBody> vendorList(@Field("latitude") double latitude, @Field("longitude") double longitude,
                                  @Field("radius") String radius);

    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getUserList(@Field("user") String user,
                                   @Field("age") String age,
                                   @Field("state") String state,
                                   @Field("city") String city);

    @GET(Constant.ALL_STORE)
    Call<StoreMainModel> getStore();

    @GET(Constant.IMAGE)
    Call<ResponseBody> getOfferList();

    @GET(Constant.POLICY)
    Call<PrivacyModel> getPolicy();

    @GET(Constant.ABOUT_US)
    Call<AboutModel> getAbout();

    @GET(Constant.FAQ)
    Call<FAQModel> getFaq();

    @GET(Constant.SIGNUP)
    Call<SignUpModel> getSignUp(@Query("user_name") String user_name,
                                @Query("user_email") String user_email,
                                @Query("user_mobile") String user_mobile,
                                @Query("user_password") String user_password);

    @GET(Constant.LOGIN)
    Call<LoginModel> getLogin(@Query("email") String email,
                              @Query("password") String password);

    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getAllLikes(@Field("id") String id);


    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getShortedList(@Field("user_id") String user_id);


}