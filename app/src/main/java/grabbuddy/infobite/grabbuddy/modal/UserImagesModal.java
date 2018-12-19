package grabbuddy.infobite.grabbuddy.modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserImagesModal implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<UserImagesDatum> data = new ArrayList<UserImagesDatum>();
    public final static Creator<UserImagesModal> CREATOR = new Creator<UserImagesModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserImagesModal createFromParcel(Parcel in) {
            return new UserImagesModal(in);
        }

        public UserImagesModal[] newArray(int size) {
            return (new UserImagesModal[size]);
        }

    }
            ;

    protected UserImagesModal(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.msg = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (UserImagesDatum.class.getClassLoader()));
    }

    public UserImagesModal() {
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

    public List<UserImagesDatum> getData() {
        return data;
    }

    public void setData(List<UserImagesDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(msg);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
