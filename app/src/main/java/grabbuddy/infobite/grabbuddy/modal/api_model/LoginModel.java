
package grabbuddy.infobite.grabbuddy.modal.api_model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("reg_id")
    @Expose
    private String regId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_mobile")
    @Expose
    private String userMobile;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    public final static Creator<LoginModel> CREATOR = new Creator<LoginModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        public LoginModel[] newArray(int size) {
            return (new LoginModel[size]);
        }

    }
    ;

    protected LoginModel(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.regId = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.userMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.userStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public LoginModel withRegId(String regId) {
        this.regId = regId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LoginModel withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LoginModel withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public LoginModel withUserMobile(String userMobile) {
        this.userMobile = userMobile;
        return this;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public LoginModel withUserStatus(String userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(regId);
        dest.writeValue(userName);
        dest.writeValue(userEmail);
        dest.writeValue(userMobile);
        dest.writeValue(userStatus);
    }

    public int describeContents() {
        return  0;
    }

}
