package grabbuddy.infobite.grabbuddy.modal.style_studio;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StyleStudioDatum implements Parcelable
{

    @SerializedName("deal_id")
    @Expose
    private String dealId;
    @SerializedName("prdct_name")
    @Expose
    private String prdctName;
    @SerializedName("prdct_link")
    @Expose
    private String prdctLink;
    @SerializedName("prdct_pic")
    @Expose
    private String prdctPic;
    @SerializedName("prdct_credit")
    @Expose
    private String prdctCredit;
    @SerializedName("deal_status")
    @Expose
    private String dealStatus;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    public final static Parcelable.Creator<StyleStudioDatum> CREATOR = new Creator<StyleStudioDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StyleStudioDatum createFromParcel(Parcel in) {
            return new StyleStudioDatum(in);
        }

        public StyleStudioDatum[] newArray(int size) {
            return (new StyleStudioDatum[size]);
        }

    }
            ;

    protected StyleStudioDatum(Parcel in) {
        this.dealId = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctName = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctLink = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctPic = ((String) in.readValue((String.class.getClassLoader())));
        this.prdctCredit = ((String) in.readValue((String.class.getClassLoader())));
        this.dealStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StyleStudioDatum() {
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getPrdctName() {
        return prdctName;
    }

    public void setPrdctName(String prdctName) {
        this.prdctName = prdctName;
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

    public String getPrdctCredit() {
        return prdctCredit;
    }

    public void setPrdctCredit(String prdctCredit) {
        this.prdctCredit = prdctCredit;
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
        dest.writeValue(prdctName);
        dest.writeValue(prdctLink);
        dest.writeValue(prdctPic);
        dest.writeValue(prdctCredit);
        dest.writeValue(dealStatus);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return 0;
    }

}