
package grabbuddy.infobite.grabbuddy.modal.term_responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermDatum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("terms_and_conditions_desc")
    @Expose
    private String termsAndConditionsDesc;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    public final static Creator<TermDatum> CREATOR = new Creator<TermDatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TermDatum createFromParcel(Parcel in) {
            return new TermDatum(in);
        }

        public TermDatum[] newArray(int size) {
            return (new TermDatum[size]);
        }

    }
    ;

    protected TermDatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.termsAndConditionsDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TermDatum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TermDatum withId(String id) {
        this.id = id;
        return this;
    }

    public String getTermsAndConditionsDesc() {
        return termsAndConditionsDesc;
    }

    public void setTermsAndConditionsDesc(String termsAndConditionsDesc) {
        this.termsAndConditionsDesc = termsAndConditionsDesc;
    }

    public TermDatum withTermsAndConditionsDesc(String termsAndConditionsDesc) {
        this.termsAndConditionsDesc = termsAndConditionsDesc;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public TermDatum withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(termsAndConditionsDesc);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return  0;
    }

}
