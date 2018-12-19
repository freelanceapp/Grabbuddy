package grabbuddy.infobite.grabbuddy.modal.today_deal_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayDealDataList implements Parcelable
{

    @SerializedName("deal_id")
    @Expose
    private String dealId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("prdct_name")
    @Expose
    private String prdctName;
    @SerializedName("prdct_price")
    @Expose
    private String prdctPrice;
    @SerializedName("prdct_discount")
    @Expose
    private String prdctDiscount;
    @SerializedName("prdct_link")
    @Expose
    private String prdctLink;
    @SerializedName("prdct_pic")
    @Expose
    private String prdctPic;
    @SerializedName("today_deals")
    @Expose
    private String todayDeals;
    @SerializedName("deal_status")
    @Expose
    private String dealStatus;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    public final static Parcelable.Creator<TodayDealDataList> CREATOR = new Creator<TodayDealDataList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TodayDealDataList createFromParcel(Parcel in) {
            return new TodayDealDataList(in);
        }

        public TodayDealDataList[] newArray(int size) {
            return (new TodayDealDataList[size]);
        }

    }
            ;

    protected TodayDealDataList(Parcel in) {
        this.dealId = ((String) in.readValue((String.class.getClassLoader())));
        this.companyId = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctName = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctDiscount = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctLink = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctPic = ((String) in.readValue((String.class.getClassLoader())));
        this.todayDeals = ((String) in.readValue((String.class.getClassLoader())));
        this.dealStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TodayDealDataList() {
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPrdctName() {
        return prdctName;
    }

    public void setPrdctName(String prdctName) {
        this.prdctName = prdctName;
    }

    public String getPrdctPrice() {
        return prdctPrice;
    }

    public void setPrdctPrice(String prdctPrice) {
        this.prdctPrice = prdctPrice;
    }

    public String getPrdctDiscount() {
        return prdctDiscount;
    }

    public void setPrdctDiscount(String prdctDiscount) {
        this.prdctDiscount = prdctDiscount;
    }

    public String getPrdctLink() {
        return prdctLink;
    }

    public void setPrdctLink(String prdctLink) {
        this.prdctLink = prdctLink;
    }

    public String getPrdctPic() {
        return prdctPic;
    }

    public void setPrdctPic(String prdctPic) {
        this.prdctPic = prdctPic;
    }

    public String getTodayDeals() {
        return todayDeals;
    }

    public void setTodayDeals(String todayDeals) {
        this.todayDeals = todayDeals;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dealId);
        dest.writeValue(companyId);
        dest.writeValue(prdctName);
        dest.writeValue(prdctPrice);
        dest.writeValue(prdctDiscount);
        dest.writeValue(prdctLink);
        dest.writeValue(prdctPic);
        dest.writeValue(todayDeals);
        dest.writeValue(dealStatus);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return 0;
    }

}