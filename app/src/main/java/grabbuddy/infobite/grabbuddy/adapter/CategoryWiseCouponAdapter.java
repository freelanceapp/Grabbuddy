package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.category_wise_data.CategoryWiseDatum;


public class CategoryWiseCouponAdapter extends RecyclerView.Adapter<CategoryWiseCouponAdapter.MyViewHolder> {

    private List<CategoryWiseDatum> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public CategoryWiseCouponAdapter(List<CategoryWiseDatum> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_wise_coupon, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescription.setText(reviewModelList.get(position).getCouponName());
        holder.tvExpires.setText("Expires" + " " + reviewModelList.get(position).getEndDate());
        holder.tvDeals.setText(reviewModelList.get(position).getCouponType());
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img, imgExclusive;
        private TextView tvDescription, tvExpires, tvDeals;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            imgExclusive = view.findViewById(R.id.imgExclusive);
            offer_img = view.findViewById(R.id.img);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvExpires = view.findViewById(R.id.tvExpires);
            tvDeals = view.findViewById(R.id.tvDeals);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
