
package grabbuddy.infobite.grabbuddy.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Coupon {

    private String name;
    private int image;
    private int imagePopular;
    private int imageOffers;


    public Coupon(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Coupon(int imagePopular) {
        this.imagePopular = imagePopular;
    }

    public Coupon(String name, int imagePopular, int imageOffers) {
        this.name = name;
        this.imagePopular = imagePopular;
        this.imageOffers = imageOffers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public int getImagePopular() {
        return imagePopular;
    }

    public void setImagePopular(int imagePopular) {
        this.imagePopular = imagePopular;
    }

    public int getImageOffers() {
        return imageOffers;
    }

    public void setImageOffers(int imageOffers) {
        this.imageOffers = imageOffers;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
