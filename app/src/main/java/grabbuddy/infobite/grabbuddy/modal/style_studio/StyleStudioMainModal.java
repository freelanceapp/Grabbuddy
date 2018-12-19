package grabbuddy.infobite.grabbuddy.modal.style_studio;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StyleStudioMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<StyleStudioDatum> data = new ArrayList<StyleStudioDatum>();
    public final static Parcelable.Creator<StyleStudioMainModal> CREATOR = new Creator<StyleStudioMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StyleStudioMainModal createFromParcel(Parcel in) {
            return new StyleStudioMainModal(in);
        }

        public StyleStudioMainModal[] newArray(int size) {
            return (new StyleStudioMainModal[size]);
        }

    }
            ;

    protected StyleStudioMainModal(Parcel in) {
        in.readList(this.data, (StyleStudioDatum.class.getClassLoader()));
    }

    public StyleStudioMainModal() {
    }

    public List<StyleStudioDatum> getData() {
        return data;
    }

    public void setData(List<StyleStudioDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
