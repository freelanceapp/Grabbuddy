package grabbuddy.infobite.grabbuddy.modal.side_banner;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SideBannerModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<SideBannerDatum> data = new ArrayList<SideBannerDatum>();
    public final static Parcelable.Creator<SideBannerModal> CREATOR = new Creator<SideBannerModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SideBannerModal createFromParcel(Parcel in) {
            return new SideBannerModal(in);
        }

        public SideBannerModal[] newArray(int size) {
            return (new SideBannerModal[size]);
        }

    }
            ;

    protected SideBannerModal(Parcel in) {
        in.readList(this.data, (SideBannerDatum.class.getClassLoader()));
    }

    public SideBannerModal() {
    }

    public List<SideBannerDatum> getData() {
        return data;
    }

    public void setData(List<SideBannerDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
