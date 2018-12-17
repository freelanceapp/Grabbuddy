package grabbuddy.infobite.grabbuddy.modal.all_category_modal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryMainModal implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<CategoryItemList> data = new ArrayList<CategoryItemList>();
    public final static Parcelable.Creator<CategoryMainModal> CREATOR = new Creator<CategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryMainModal createFromParcel(Parcel in) {
            return new CategoryMainModal(in);
        }

        public CategoryMainModal[] newArray(int size) {
            return (new CategoryMainModal[size]);
        }

    };

    protected CategoryMainModal(Parcel in) {
        in.readList(this.data, (CategoryItemList.class.getClassLoader()));
    }

    public CategoryMainModal() {
    }

    public List<CategoryItemList> getData() {
        return data;
    }

    public void setData(List<CategoryItemList> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}