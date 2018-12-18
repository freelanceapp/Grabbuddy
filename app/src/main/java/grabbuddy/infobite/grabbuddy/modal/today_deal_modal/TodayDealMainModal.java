package grabbuddy.infobite.grabbuddy.modal.today_deal_modal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayDealMainModal implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<TodayDealDataList> data = new ArrayList<TodayDealDataList>();
    public final static Parcelable.Creator<TodayDealMainModal> CREATOR = new Creator<TodayDealMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TodayDealMainModal createFromParcel(Parcel in) {
            return new TodayDealMainModal(in);
        }

        public TodayDealMainModal[] newArray(int size) {
            return (new TodayDealMainModal[size]);
        }

    };

    protected TodayDealMainModal(Parcel in) {
        in.readList(this.data, (TodayDealDataList.class.getClassLoader()));
    }

    public TodayDealMainModal() {
    }

    public List<TodayDealDataList> getData() {
        return data;
    }

    public void setData(List<TodayDealDataList> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
