
package grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model;

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
    @SerializedName("privacy_policy_desc")
    @Expose
    private String privacyPolicyDesc;
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
        this.privacyPolicyDesc = ((String) in.readValue((String.class.getClassLoader())));
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

    public String getPrivacyPolicyDesc() {
        return privacyPolicyDesc;
    }

    public void setPrivacyPolicyDesc(String privacyPolicyDesc) {
        this.privacyPolicyDesc = privacyPolicyDesc;
    }

    public Datum withPrivacyPolicyDesc(String privacyPolicyDesc) {
        this.privacyPolicyDesc = privacyPolicyDesc;
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
        dest.writeValue(privacyPolicyDesc);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return  0;
    }

}
