package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.modal.Coupon;


public class TodaysOfferAdapter extends RecyclerView.Adapter<TodaysOfferAdapter.MyViewHolder> {

    private List<Coupon> reviewModelList;
    private Context context;

    public TodaysOfferAdapter(List<Coupon> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todays_offers, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescription.setText(reviewModelList.get(position).getName());
        holder.offer_img.setImageResource(reviewModelList.get(position).getImagePopular());
        holder.imgExclusive.setImageResource(reviewModelList.get(position).getImageOffers());
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img, imgExclusive;
        private TextView tvDescription;

        public MyViewHolder(View view) {
            super(view);
            imgExclusive = view.findViewById(R.id.imgExclusive);
            offer_img = view.findViewById(R.id.img);
            tvDescription = view.findViewById(R.id.tvDescription);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
