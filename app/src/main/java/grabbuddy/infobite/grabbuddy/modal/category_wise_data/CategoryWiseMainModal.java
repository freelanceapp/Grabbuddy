package grabbuddy.infobite.grabbuddy.modal.category_wise_data;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryWiseMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<CategoryWiseDatum> data = new ArrayList<CategoryWiseDatum>();
    public final static Parcelable.Creator<CategoryWiseMainModal> CREATOR = new Creator<CategoryWiseMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryWiseMainModal createFromParcel(Parcel in) {
            return new CategoryWiseMainModal(in);
        }

        public CategoryWiseMainModal[] newArray(int size) {
            return (new CategoryWiseMainModal[size]);
        }

    }
            ;

    protected CategoryWiseMainModal(Parcel in) {
        in.readList(this.data, (CategoryWiseDatum.class.getClassLoader()));
    }

    public CategoryWiseMainModal() {
    }

    public List<CategoryWiseDatum> getData() {
        return data;
    }

    public void setData(List<CategoryWiseDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}