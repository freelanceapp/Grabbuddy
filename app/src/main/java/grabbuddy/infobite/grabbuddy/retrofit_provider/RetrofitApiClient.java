package grabbuddy.infobite.grabbuddy.retrofit_provider;

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
import grabbuddy.infobite.grabbuddy.modal.search_modal_data.SearchStoreMainModal;
import grabbuddy.infobite.grabbuddy.modal.side_banner.SideBannerModal;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioMainModal;
import grabbuddy.infobite.grabbuddy.modal.success_modal.MarriageSuccessModal;
import grabbuddy.infobite.grabbuddy.modal.term_responce.TermModel;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealMainModal;
import grabbuddy.infobite.grabbuddy.modal.tokan_responce.TokenModel;
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

    @GET(Constant.FOROGOT_PASSWORD)
    Call<SignUpModel> getForgotPassword(@Query("useremail") String useremail);

    @GET(Constant.ALL_STORE)
    Call<StoreMainModel> getStore();

    @GET(Constant.SEARCH_STORE)
    Call<SearchStoreMainModal> getSearchStore();


    @GET(Constant.IMAGE)
    Call<ResponseBody> getOfferList();

    @GET(Constant.POLICY)
    Call<PrivacyModel> getPolicy();

    @GET(Constant.ABOUT_US)
    Call<AboutModel> getAbout();

    @GET(Constant.FAQ)
    Call<FAQModel> getFaq();

    @GET(Constant.TERM_DATA)
    Call<TermModel> getTerm();

    @GET(Constant.BANNER)
    Call<BannerModel> getBanner();

    @GET(Constant.SIGNUP)
    Call<SignUpModel> getSignUp(@Query("user_name") String user_name,
                                @Query("user_email") String user_email,
                                @Query("user_mobile") String user_mobile,
                                @Query("user_password") String user_password);

    @GET(Constant.SUBSCRIBE)
    Call<SignUpModel> getSubscribe(@Query("email") String email);

    @GET(Constant.LOGIN)
    Call<LoginModel> getLogin(@Query("email") String email,
                              @Query("password") String password);

    @FormUrlEncoded
    @POST(Constant.COUTACT)
    Call<SignUpModel> getContact(@Field("full_name") String full_name,
                                 @Field("email_id ") String email_id,
                                 @Field("business ") String business,
                                 @Field("contact_no ") String contact_no,
                                 @Field("messege ") String messege);





    @GET(Constant.APP_TOKEN)
    Call<TokenModel> getToken(@Query("user_id") String user_id,
                                @Query("user_ip") String user_ip,
                                @Query("token") String token);


    @GET(Constant.TODAY_DEAL)
    Call<TodayDealMainModal> todayDealData();

    @GET(Constant.STYLE_STUDIO)
    Call<StyleStudioMainModal> styleStudio();

    @GET(Constant.COUPON)
    Call<CouponModel> getCoupon();

    @GET(Constant.SIDE_BANNER)
    Call<SideBannerModal> sideBanner();

}