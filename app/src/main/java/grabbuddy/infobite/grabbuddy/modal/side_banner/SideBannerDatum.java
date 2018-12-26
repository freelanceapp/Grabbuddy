package grabbuddy.infobite.grabbuddy.modal.side_banner;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SideBannerDatum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_link")
    @Expose
    private String offerLink;
    @SerializedName("offer_picture")
    @Expose
    private String offerPicture;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    public final static Parcelable.Creator<SideBannerDatum> CREATOR = new Creator<SideBannerDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SideBannerDatum createFromParcel(Parcel in) {
            return new SideBannerDatum(in);
        }

        public SideBannerDatum[] newArray(int size) {
            return (new SideBannerDatum[size]);
        }

    }
            ;

    protected SideBannerDatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.offerName = ((String) in.readValue((String.class.getClassLoader())));
        this.offerLink = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPicture = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SideBannerDatum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferLink() {
        return offerLink;
    }

    public void setOfferLink(String offerLink) {
        this.offerLink = offerLink;
    }

    public String getOfferPicture() {
        return offerPicture;
    }

    public void setOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
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
        dest.writeValue(offerName);
        dest.writeValue(offerLink);
        dest.writeValue(offerPicture);
        dest.writeValue(status);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return 0;
    }

}