package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.all_category_modal.CategoryItemList;


public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.MyViewHolder> {

    private List<CategoryItemList> itemLists;
    private Context context;
    private View.OnClickListener onClickListener;

    public AllCategoryAdapter(List<CategoryItemList> itemLists, Context context, View.OnClickListener onClickListener) {
        this.itemLists = itemLists;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categories, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(itemLists.get(position).getCatName());

        holder.llStore.setTag(position);
        holder.llStore.setOnClickListener(onClickListener);

        Picasso.with(context).load(Constant.IMAGE + itemLists.get(position).getCatLogo()).into(holder.offer_img);
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView offer_img;
        private TextView tvName;
        private LinearLayout llStore;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            offer_img = view.findViewById(R.id.img);
            llStore = view.findViewById(R.id.llStore);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
