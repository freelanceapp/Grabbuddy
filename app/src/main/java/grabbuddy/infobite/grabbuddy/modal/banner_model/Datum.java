
package grabbuddy.infobite.grabbuddy.modal.banner_model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
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
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.offerName = ((String) in.readValue((String.class.getClassLoader())));
        this.offerLink = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPicture = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Datum withId(String id) {
        this.id = id;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Datum withOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getOfferLink() {
        return offerLink;
    }

    public void setOfferLink(String offerLink) {
        this.offerLink = offerLink;
    }

    public Datum withOfferLink(String offerLink) {
        this.offerLink = offerLink;
        return this;
    }

    public String getOfferPicture() {
        return offerPicture;
    }

    public void setOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
    }

    public Datum withOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Datum withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Datum withDateTime(String dateTime) {
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
