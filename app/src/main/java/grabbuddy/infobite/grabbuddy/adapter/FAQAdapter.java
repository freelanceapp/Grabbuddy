package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.modal.api_model.faq_model.Datum;


public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {

    private List<Datum> reviewModelList;
    private Context context;
    private View.OnClickListener onClickListener;

    public FAQAdapter(ArrayList<Datum> datumArrayList, Context context){
        this.reviewModelList = datumArrayList;
        this.context = context;
    }

    public FAQAdapter(List<Datum> reviewModelList, Context context, View.OnClickListener onClickListener) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDate.setText(reviewModelList.get(position).getDateTime());
        holder.tvQus.setText(reviewModelList.get(position).getQuestion());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvAns.setText(Html.fromHtml(reviewModelList.get(position).getAnswer(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvAns.setText(Html.fromHtml(reviewModelList.get(position).getAnswer()));
        }

    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvDate,tvQus, tvAns;

        public MyViewHolder(View view) {
            super(view);

            tvDate = view.findViewById(R.id.tv_date);
            tvAns = view.findViewById(R.id.tv_ans);
            tvQus = view.findViewById(R.id.tv_qus);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
