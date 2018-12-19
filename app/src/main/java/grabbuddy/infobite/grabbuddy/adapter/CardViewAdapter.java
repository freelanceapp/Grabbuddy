package grabbuddy.infobite.grabbuddy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.today_deal_modal.TodayDealDataList;

public class CardViewAdapter extends BaseAdapter {

    private List<TodayDealDataList> dealDataLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public CardViewAdapter(Context mContext, List<TodayDealDataList> dealDataLists, View.OnClickListener onClickListener) {
        this.mContext = mContext;
        this.dealDataLists = dealDataLists;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        if (dealDataLists.size() > 7) {
            return 7;
        } else {
            return dealDataLists.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return dealDataLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card_item, parent, false);
        }
        setIds(convertView, position);
        return convertView;
    }

    private void setIds(View view, int position) {
        CardView cardViewDeal = (CardView) view.findViewById(R.id.cardViewDeal);
        TextView tvOff, tvCompanyName, tvDescription, tvOfferPrice, tvRealPrice;
        tvOff = (TextView) view.findViewById(R.id.tvOff);
        tvCompanyName = (TextView) view.findViewById(R.id.tvCompanyName);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvOfferPrice = (TextView) view.findViewById(R.id.tvOfferPrice);
        tvRealPrice = (TextView) view.findViewById(R.id.tvRealPrice);

        ImageView imageView = view.findViewById(R.id.imageView);
        Picasso.with(mContext)
                .load(Constant.IMAGE + dealDataLists.get(position).getPrdctPic())
                .placeholder(R.drawable.img_a)
                .into(imageView);

        cardViewDeal.setTag(position);
        cardViewDeal.setOnClickListener(onClickListener);

        tvOff.setText(dealDataLists.get(position).getPrdctDiscount() + "%" + " " + "Off");
        tvCompanyName.setText(dealDataLists.get(position).getPrdctLink());
        tvDescription.setText(dealDataLists.get(position).getPrdctName());
        tvRealPrice.setText("Rs." + dealDataLists.get(position).getPrdctPrice());

        String strPercent = dealDataLists.get(position).getPrdctDiscount();
        String strAmount = dealDataLists.get(position).getPrdctPrice();
        if (strPercent.isEmpty()) {
            strPercent = "0";
        }
        if (strAmount.isEmpty()) {
            strAmount = "0";
        }
        double percent = Double.parseDouble(strPercent);
        double originalAmount = Double.parseDouble(strAmount);

        double percentAmount = (originalAmount * percent) / 100;

        originalAmount = originalAmount - percentAmount;

        tvOfferPrice.setText("Rs." + originalAmount);
    }
}
