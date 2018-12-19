package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.squareup.picasso.Picasso;
import java.util.List;
import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.UserImagesDatum;
import grabbuddy.infobite.grabbuddy.modal.banner_model.Datum;

public class SlideShowPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Datum> searchArrayList;

    public SlideShowPagerAdapter(Context context, List<Datum> searchArrayList) {
        mContext = context;
        this.searchArrayList = searchArrayList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slidingimages_layout, container, false);

        //final ProgressBar progressImage = (ProgressBar) itemView.findViewById(R.id.progressImage);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        Log.e("Image","..."+searchArrayList.get(position).getOfferPicture());
        Picasso.with(mContext).load(Constant.IMAGE + searchArrayList.get(position).getOfferPicture()).placeholder(R.drawable.app_logo_b).into(imageView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
