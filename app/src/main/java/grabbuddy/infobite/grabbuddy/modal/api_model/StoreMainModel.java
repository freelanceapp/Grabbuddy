
package grabbuddy.infobite.grabbuddy.modal.api_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreMainModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<StoreMainModel> CREATOR = new Creator<StoreMainModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StoreMainModel createFromParcel(Parcel in) {
            return new StoreMainModel(in);
        }

        public StoreMainModel[] newArray(int size) {
            return (new StoreMainModel[size]);
        }

    }
    ;

    protected StoreMainModel(Parcel in) {
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public StoreMainModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public StoreMainModel withData(List<Datum> data) {
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
