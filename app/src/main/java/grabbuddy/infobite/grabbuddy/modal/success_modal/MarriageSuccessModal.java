package grabbuddy.infobite.grabbuddy.modal.success_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MarriageSuccessModal implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("All_images")
    @Expose
    private List<SuccessImage> allImages = new ArrayList<SuccessImage>();
    public final static Parcelable.Creator<MarriageSuccessModal> CREATOR = new Creator<MarriageSuccessModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MarriageSuccessModal createFromParcel(Parcel in) {
            return new MarriageSuccessModal(in);
        }

        public MarriageSuccessModal[] newArray(int size) {
            return (new MarriageSuccessModal[size]);
        }

    }
            ;

    protected MarriageSuccessModal(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.allImages, (SuccessImage.class.getClassLoader()));
    }

    public MarriageSuccessModal() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SuccessImage> getAllImages() {
        return allImages;
    }

    public void setAllImages(List<SuccessImage> allImages) {
        this.allImages = allImages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(allImages);
    }

    public int describeContents() {
        return 0;
    }

}
