
package grabbuddy.infobite.grabbuddy.modal.coupon_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponModel implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<CouponDatum> data = new ArrayList<CouponDatum>();
    public final static Creator<CouponModel> CREATOR = new Creator<CouponModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CouponModel createFromParcel(Parcel in) {
            return new CouponModel(in);
        }

        public CouponModel[] newArray(int size) {
            return (new CouponModel[size]);
        }

    }
    ;

    protected CouponModel(Parcel in) {
        in.readList(this.data, (CouponDatum.class.getClassLoader()));
    }

    public CouponModel() {
    }

    public List<CouponDatum> getData() {
        return data;
    }

    public void setData(List<CouponDatum> data) {
        this.data = data;
    }

    public CouponModel withData(List<CouponDatum> data) {
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
