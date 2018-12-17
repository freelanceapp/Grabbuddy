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


public class StylesStudioAdapter extends RecyclerView.Adapter<StylesStudioAdapter.MyViewHolder> {

    private List<Coupon> reviewModelList;
    private Context context;

    public StylesStudioAdapter(List<Coupon> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_style_studio, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.offer_img.setImageResource(reviewModelList.get(position).getImagePopular());
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img;

        public MyViewHolder(View view) {
            super(view);
            offer_img = view.findViewById(R.id.img);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
