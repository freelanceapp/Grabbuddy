
package grabbuddy.infobite.grabbuddy.modal.banner_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerDatum implements Parcelable
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
    public final static Creator<BannerDatum> CREATOR = new Creator<BannerDatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BannerDatum createFromParcel(Parcel in) {
            return new BannerDatum(in);
        }

        public BannerDatum[] newArray(int size) {
            return (new BannerDatum[size]);
        }

    }
    ;

    protected BannerDatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.offerName = ((String) in.readValue((String.class.getClassLoader())));
        this.offerLink = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPicture = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BannerDatum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BannerDatum withId(String id) {
        this.id = id;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public BannerDatum withOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getOfferLink() {
        return offerLink;
    }

    public void setOfferLink(String offerLink) {
        this.offerLink = offerLink;
    }

    public BannerDatum withOfferLink(String offerLink) {
        this.offerLink = offerLink;
        return this;
    }

    public String getOfferPicture() {
        return offerPicture;
    }

    public void setOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
    }

    public BannerDatum withOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BannerDatum withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BannerDatum withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
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
        return  0;
    }

}
