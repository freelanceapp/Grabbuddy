
package grabbuddy.infobite.grabbuddy.modal.api_model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<SignUpModel> CREATOR = new Creator<SignUpModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SignUpModel createFromParcel(Parcel in) {
            return new SignUpModel(in);
        }

        public SignUpModel[] newArray(int size) {
            return (new SignUpModel[size]);
        }

    }
    ;

    protected SignUpModel(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SignUpModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignUpModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
