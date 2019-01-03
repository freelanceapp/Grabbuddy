
package grabbuddy.infobite.grabbuddy.modal.coupon_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponDatum implements Parcelable
{

    @SerializedName("company_logo")
    @Expose
    private String company_logo;
    @SerializedName("company_name")
    @Expose
    private String company_name;
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
    public final static Creator<CouponDatum> CREATOR = new Creator<CouponDatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CouponDatum createFromParcel(Parcel in) {
            return new CouponDatum(in);
        }

        public CouponDatum[] newArray(int size) {
            return (new CouponDatum[size]);
        }

    }
    ;

    protected CouponDatum(Parcel in) {
        this.company_logo = ((String) in.readValue((String.class.getClassLoader())));
        this.company_name = ((String) in.readValue((String.class.getClassLoader())));
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

    public CouponDatum() {
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CouponDatum withId(String id) {
        this.id = id;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CouponDatum withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CouponDatum withCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public CouponDatum withCouponName(String couponName) {
        this.couponName = couponName;
        return this;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public CouponDatum withCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public String getEditorPick() {
        return editorPick;
    }

    public void setEditorPick(String editorPick) {
        this.editorPick = editorPick;
    }

    public CouponDatum withEditorPick(String editorPick) {
        this.editorPick = editorPick;
        return this;
    }

    public String getCouponOffer() {
        return couponOffer;
    }

    public void setCouponOffer(String couponOffer) {
        this.couponOffer = couponOffer;
    }

    public CouponDatum withCouponOffer(String couponOffer) {
        this.couponOffer = couponOffer;
        return this;
    }

    public String getCouponLink() {
        return couponLink;
    }

    public void setCouponLink(String couponLink) {
        this.couponLink = couponLink;
    }

    public CouponDatum withCouponLink(String couponLink) {
        this.couponLink = couponLink;
        return this;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    public CouponDatum withCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
        return this;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public CouponDatum withCouponType(String couponType) {
        this.couponType = couponType;
        return this;
    }

    public String getFevCoupon() {
        return fevCoupon;
    }

    public void setFevCoupon(String fevCoupon) {
        this.fevCoupon = fevCoupon;
    }

    public CouponDatum withFevCoupon(String fevCoupon) {
        this.fevCoupon = fevCoupon;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public CouponDatum withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CouponDatum withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CouponDatum withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public CouponDatum withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(company_name);
        dest.writeValue(id);
        dest.writeValue(company_logo);
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
        return  0;
    }

}
