package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.style_studio.StyleStudioDatum;


public class TodaysOfferAdapter extends RecyclerView.Adapter<TodaysOfferAdapter.MyViewHolder> {

    private List<StyleStudioDatum> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public TodaysOfferAdapter(List<StyleStudioDatum> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todays_offers, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescription.setText(reviewModelList.get(position).getPrdctName());

        String strDate = reviewModelList.get(position).getDateTime();
        holder.tvDateTime.setText(strDate);

        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(onClickListener);

        Picasso.with(context)
                .load(Constant.IMAGE + reviewModelList.get(position).getPrdctPic())
                .placeholder(R.drawable.default_img)
                .into(holder.offer_img);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img, imgExclusive;
        private TextView tvDescription, tvDateTime;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            imgExclusive = view.findViewById(R.id.imgExclusive);
            offer_img = view.findViewById(R.id.img);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvDateTime = view.findViewById(R.id.tvDateTime);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
