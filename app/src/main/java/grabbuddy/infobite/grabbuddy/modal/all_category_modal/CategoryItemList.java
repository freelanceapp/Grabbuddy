package grabbuddy.infobite.grabbuddy.modal.all_category_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryItemList implements Parcelable
{

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_logo")
    @Expose
    private String catLogo;
    @SerializedName("cat_status")
    @Expose
    private String catStatus;
    @SerializedName("top_cat_status")
    @Expose
    private String topCatStatus;
    @SerializedName("cat_date")
    @Expose
    private String catDate;
    public final static Parcelable.Creator<CategoryItemList> CREATOR = new Creator<CategoryItemList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryItemList createFromParcel(Parcel in) {
            return new CategoryItemList(in);
        }

        public CategoryItemList[] newArray(int size) {
            return (new CategoryItemList[size]);
        }

    }
            ;

    protected CategoryItemList(Parcel in) {
        this.catId = ((String) in.readValue((String.class.getClassLoader())));
        this.catName = ((String) in.readValue((String.class.getClassLoader())));
        this.catLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.catStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.topCatStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.catDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CategoryItemList() {
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatLogo() {
        return catLogo;
    }

    public void setCatLogo(String catLogo) {
        this.catLogo = catLogo;
    }

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }

    public String getTopCatStatus() {
        return topCatStatus;
    }

    public void setTopCatStatus(String topCatStatus) {
        this.topCatStatus = topCatStatus;
    }

    public String getCatDate() {
        return catDate;
    }

    public void setCatDate(String catDate) {
        this.catDate = catDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(catId);
        dest.writeValue(catName);
        dest.writeValue(catLogo);
        dest.writeValue(catStatus);
        dest.writeValue(topCatStatus);
        dest.writeValue(catDate);
    }

    public int describeContents() {
        return 0;
    }

}
