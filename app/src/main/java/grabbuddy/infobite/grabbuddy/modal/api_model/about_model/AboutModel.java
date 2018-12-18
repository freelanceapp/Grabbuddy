
package grabbuddy.infobite.grabbuddy.modal.api_model.about_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<AboutModel> CREATOR = new Creator<AboutModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AboutModel createFromParcel(Parcel in) {
            return new AboutModel(in);
        }

        public AboutModel[] newArray(int size) {
            return (new AboutModel[size]);
        }

    }
    ;

    protected AboutModel(Parcel in) {
        in.readList(this.data, (grabbuddy.infobite.grabbuddy.modal.api_model.about_model.Datum.class.getClassLoader()));
    }

    public AboutModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public AboutModel withData(List<Datum> data) {
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
