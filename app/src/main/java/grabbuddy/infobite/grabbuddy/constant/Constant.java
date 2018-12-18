package grabbuddy.infobite.grabbuddy.constant;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.utils.BaseActivity;

public class Constant {
    public static final String MY_PREFS_NAME = "Grabbuddy";
    /*http://grabbuddy.in/androidapi/category-wise-product.php?category_id=1*/
    public static final String BASE_URL = " http://grabbuddy.in/";
    public static final String SIGNUP = "androidapi/register.php?";
    public static final String ALL_CATEGORY = "androidapi/all-cat.php";
    public static final String CATEGORY_WISE = "androidapi/category-wise-product.php?";
    public static final String COMPANY_WISE = "androidapi/company-wise-product.php?";

    public static final String USER_PROFILE = "api/user-profile.php";
    public static final String LOGIN = "androidapi/login.php";
    public static final String VERIFICATION = "api/user-verification.php";
    public static final String VENDOR_DETAIL = "api/vendor-details.php";
    public static final String VENDOR_LIST = "api/vendor-list.php";
    public static final String FOROGOT_PASSWORD = "androidapi/forgot.php";
    public static final String NOTIFICATION_LIST = "api/select-notification.php";
    public static final String OFFER_LIST = "api/coupon_list.php";
    public static final String ALL_STORE = "androidapi/all-store.php";
    public static final String IMAGE = "https://www.grabbuddy.in/admin/images/company_picture/";
    public static final String POLICY = "androidapi/privacy.php";
    public static final String ABOUT_US = "androidapi/about.php";
    public static final String FAQ = "androidapi/faq.php";
    public static String USER_NAME = "";
    public static String USER_ID = "";
    public static String USER_EMAIL = "";
    public static String USER_MOBILE = "";


    public static final String[] nameArray = {"Snapdeal", "Jumia", "Amazon Prime", "Amazon",
            "Snapdeal", "Jumia", "Amazon Prime", "Amazon", "Snapdeal", "Jumia", "Amazon Prime", "Amazon"};
    public static final Integer[] drawableArray = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_d,
            R.drawable.img_d, R.drawable.img_a, R.drawable.img_b, R.drawable.img_d,
            R.drawable.img_d, R.drawable.img_a, R.drawable.img_b, R.drawable.img_d,
            R.drawable.img_d};

    public static final Integer[] exclusiveImage = {R.drawable.ic_star, R.drawable.ic_heart, R.drawable.ic_star, R.drawable.ic_heart,
            R.drawable.ic_star, R.drawable.ic_heart};

    public static final Integer[] images = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_d,
            R.drawable.img_d, R.drawable.img_a, R.drawable.img_b};

    public static final String CouponFragment = "CouponFragment";
    public static final String DealsFragment = "DealsFragment";
    public static final String StylesStudioFragment = "StylesStudioFragment";

    //Preference
    public static final String IMAGE_PREF = "img_pref";
}
