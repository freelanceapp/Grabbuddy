
package grabbuddy.infobite.grabbuddy.modal.api_model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_url")
    @Expose
    private String companyUrl;
    @SerializedName("company_logo")
    @Expose
    private String companyLogo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("top_store_status")
    @Expose
    private String topStoreStatus;
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
        this.cId = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.companyUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.companyLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.topStoreStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public Datum withCId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Datum withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public Datum withCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
        return this;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public Datum withCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
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

    public String getTopStoreStatus() {
        return topStoreStatus;
    }

    public void setTopStoreStatus(String topStoreStatus) {
        this.topStoreStatus = topStoreStatus;
    }

    public Datum withTopStoreStatus(String topStoreStatus) {
        this.topStoreStatus = topStoreStatus;
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
        dest.writeValue(cId);
        dest.writeValue(companyName);
        dest.writeValue(companyUrl);
        dest.writeValue(companyLogo);
        dest.writeValue(status);
        dest.writeValue(topStoreStatus);
        dest.writeValue(dateTime);
    }

    public int describeContents() {
        return  0;
    }

}
