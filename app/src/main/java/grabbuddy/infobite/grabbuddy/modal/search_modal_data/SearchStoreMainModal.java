package grabbuddy.infobite.grabbuddy.modal.search_modal_data;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchStoreMainModal implements Parcelable
{

    @SerializedName("data")
    @Expose
    private List<SearchStoreDatum> data = new ArrayList<SearchStoreDatum>();
    public final static Parcelable.Creator<SearchStoreMainModal> CREATOR = new Creator<SearchStoreMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchStoreMainModal createFromParcel(Parcel in) {
            return new SearchStoreMainModal(in);
        }

        public SearchStoreMainModal[] newArray(int size) {
            return (new SearchStoreMainModal[size]);
        }

    }
            ;

    protected SearchStoreMainModal(Parcel in) {
        in.readList(this.data, (SearchStoreDatum.class.getClassLoader()));
    }

    public SearchStoreMainModal() {
    }

    public List<SearchStoreDatum> getData() {
        return data;
    }

    public void setData(List<SearchStoreDatum> data) {
        this.data = data;
    }

    public SearchStoreMainModal withData(List<SearchStoreDatum> data) {
        this.data = data;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
