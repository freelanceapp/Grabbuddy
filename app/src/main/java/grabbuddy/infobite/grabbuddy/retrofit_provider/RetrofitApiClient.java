package grabbuddy.infobite.grabbuddy.retrofit_provider;

import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.api_model.StoreMainModel;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryMainModal;
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

public interface RetrofitApiClient {

    @GET(Constant.ALL_CATEGORY)
    Call<CategoryMainModal> allCategory();

    @FormUrlEncoded
    @POST(Constant.USER_PROFILE)
    Call<ResponseBody> userProfile(@Field("user_id") String user_id);

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

    @GET(Constant.NOTIFICATION_LIST)
    Call<ResponseBody> getNotificationList();




    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getAllLikes(@Field("id") String id);



    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getShortedList(@Field("user_id") String user_id);


}