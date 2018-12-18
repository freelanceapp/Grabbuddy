
package grabbuddy.infobite.grabbuddy.modal.api_model.faq_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FAQModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    public final static Creator<FAQModel> CREATOR = new Creator<FAQModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FAQModel createFromParcel(Parcel in) {
            return new FAQModel(in);
        }

        public FAQModel[] newArray(int size) {
            return (new FAQModel[size]);
        }

    }
    ;

    protected FAQModel(Parcel in) {
        in.readList(this.data, (grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.Datum.class.getClassLoader()));
    }

    public FAQModel() {
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public FAQModel withData(List<Datum> data) {
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
