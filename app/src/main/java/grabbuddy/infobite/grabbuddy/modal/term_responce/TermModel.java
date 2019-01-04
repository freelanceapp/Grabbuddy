
package grabbuddy.infobite.grabbuddy.modal.term_responce;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<TermDatum> data = new ArrayList<TermDatum>();
    public final static Creator<TermModel> CREATOR = new Creator<TermModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TermModel createFromParcel(Parcel in) {
            return new TermModel(in);
        }

        public TermModel[] newArray(int size) {
            return (new TermModel[size]);
        }

    }
    ;

    protected TermModel(Parcel in) {
        in.readList(this.data, (TermDatum.class.getClassLoader()));
    }

    public TermModel() {
    }

    public List<TermDatum> getData() {
        return data;
    }

    public void setData(List<TermDatum> data) {
        this.data = data;
    }

    public TermModel withData(List<TermDatum> data) {
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
