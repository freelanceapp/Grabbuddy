package grabbuddy.infobite.grabbuddy.modal.search_modal_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchStoreDatum implements Parcelable
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
    public final static Parcelable.Creator<SearchStoreDatum> CREATOR = new Creator<SearchStoreDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchStoreDatum createFromParcel(Parcel in) {
            return new SearchStoreDatum(in);
        }

        public SearchStoreDatum[] newArray(int size) {
            return (new SearchStoreDatum[size]);
        }

    }
            ;

    protected SearchStoreDatum(Parcel in) {
        this.cId = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.companyUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.companyLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.topStoreStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SearchStoreDatum() {
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public SearchStoreDatum withCId(String cId) {
        this.cId = cId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SearchStoreDatum withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public SearchStoreDatum withCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
        return this;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public SearchStoreDatum withCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SearchStoreDatum withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTopStoreStatus() {
        return topStoreStatus;
    }

    public void setTopStoreStatus(String topStoreStatus) {
        this.topStoreStatus = topStoreStatus;
    }

    public SearchStoreDatum withTopStoreStatus(String topStoreStatus) {
        this.topStoreStatus = topStoreStatus;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public SearchStoreDatum withDateTime(String dateTime) {
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
        return 0;
    }

}