
package grabbuddy.infobite.grabbuddy.modal.banner_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<BannerModel> CREATOR = new Creator<BannerModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BannerModel createFromParcel(Parcel in) {
            return new BannerModel(in);
        }

        public BannerModel[] newArray(int size) {
            return (new BannerModel[size]);
        }

    }
    ;

    protected BannerModel(Parcel in) {
        in.readList(this.data, (grabbuddy.infobite.grabbuddy.modal.banner_model.Datum.class.getClassLoader()));
    }

    public BannerModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public BannerModel withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
