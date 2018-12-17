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


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private List<Coupon> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public OffersAdapter(List<Coupon> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_top_stores, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(reviewModelList.get(position).getName());
        holder.offer_img.setImageResource(reviewModelList.get(position).getImage());

        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        public ImageView offer_img;
        private TextView textView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            offer_img = view.findViewById(R.id.img);
            textView = view.findViewById(R.id.tvName);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
