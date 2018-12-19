package grabbuddy.infobite.grabbuddy.modal.success_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessImage implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("img")
    @Expose
    private String img;
    public final static Parcelable.Creator<SuccessImage> CREATOR = new Creator<SuccessImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SuccessImage createFromParcel(Parcel in) {
            return new SuccessImage(in);
        }

        public SuccessImage[] newArray(int size) {
            return (new SuccessImage[size]);
        }

    }
            ;

    protected SuccessImage(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.img = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SuccessImage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(img);
    }

    public int describeContents() {
        return 0;
    }

}
