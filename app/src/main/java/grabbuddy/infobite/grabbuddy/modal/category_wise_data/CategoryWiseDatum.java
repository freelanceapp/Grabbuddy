package grabbuddy.infobite.grabbuddy.modal.category_wise_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryWiseDatum implements Parcelable ,Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("coupon_name")
    @Expose
    private String couponName;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("editor_pick")
    @Expose
    private String editorPick;
    @SerializedName("coupon_offer")
    @Expose
    private String couponOffer;
    @SerializedName("coupon_link")
    @Expose
    private String couponLink;
    @SerializedName("coupon_desc")
    @Expose
    private String couponDesc;
    @SerializedName("coupon_type")
    @Expose
    private String couponType;
    @SerializedName("fev_coupon")
    @Expose
    private String fevCoupon;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    public final static Parcelable.Creator<CategoryWiseDatum> CREATOR = new Creator<CategoryWiseDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryWiseDatum createFromParcel(Parcel in) {
            return new CategoryWiseDatum(in);
        }

        public CategoryWiseDatum[] newArray(int size) {
            return (new CategoryWiseDatum[size]);
        }

    }
            ;

    protected CategoryWiseDatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.companyId = ((String) in.readValue((String.class.getClassLoader())));
        this.couponName = ((String) in.readValue((String.class.getClassLoader())));
        this.couponCode = ((String) in.readValue((String.class.getClassLoader())));
        this.editorPick = ((String) in.readValue((String.class.getClassLoader())));
        this.couponOffer = ((String) in.readValue((String.class.getClassLoader())));
        this.couponLink = ((String) in.readValue((String.class.getClassLoader())));
        this.couponDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.couponType = ((String) in.readValue((String.class.getClassLoader())));
        this.fevCoupon = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CategoryWiseDatum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getEditorPick() {
        return editorPick;
    }

    public void setEditorPick(String editorPick) {
        this.editorPick = editorPick;
    }

    public String getCouponOffer() {
        return couponOffer;
    }

    public void setCouponOffer(String couponOffer) {
        this.couponOffer = couponOffer;
    }

    public String getCouponLink() {
        return couponLink;
    }

    public void setCouponLink(String couponLink) {
        this.couponLink = couponLink;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getFevCoupon() {
        return fevCoupon;
    }

    public void setFevCoupon(String fevCoupon) {
        this.fevCoupon = fevCoupon;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(categoryId);
        dest.writeValue(companyId);
        dest.writeValue(couponName);
        dest.writeValue(couponCode);
        dest.writeValue(editorPick);
        dest.writeValue(couponOffer);
        dest.writeValue(couponLink);
        dest.writeValue(couponDesc);
        dest.writeValue(couponType);
        dest.writeValue(fevCoupon);
        dest.writeValue(startDate);
        dest.writeValue(endDate);
        dest.writeValue(status);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return 0;
    }

}

