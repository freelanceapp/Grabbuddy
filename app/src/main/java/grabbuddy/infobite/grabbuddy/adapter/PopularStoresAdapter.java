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
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;

public class PopularStoresAdapter extends RecyclerView.Adapter<PopularStoresAdapter.MyViewHolder> {

    private List<Datum> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public PopularStoresAdapter(List<Datum> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_stores, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(Constant.IMAGE + reviewModelList.get(position).getCompanyLogo())
                .placeholder(R.drawable.app_logo_b)
                .into(holder.offer_img);
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            offer_img = view.findViewById(R.id.img);
            cardView = view.findViewById(R.id.cardViewPopular);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
