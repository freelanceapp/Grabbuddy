
package grabbuddy.infobite.grabbuddy.modal.api_model.privacy_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrivacyModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<PrivacyModel> CREATOR = new Creator<PrivacyModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PrivacyModel createFromParcel(Parcel in) {
            return new PrivacyModel(in);
        }

        public PrivacyModel[] newArray(int size) {
            return (new PrivacyModel[size]);
        }

    }
    ;

    protected PrivacyModel(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public PrivacyModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public PrivacyModel withData(List<Datum> data) {
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
