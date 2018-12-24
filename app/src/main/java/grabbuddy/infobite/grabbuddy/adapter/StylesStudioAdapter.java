package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class StylesStudioAdapter extends RecyclerView.Adapter<StylesStudioAdapter.MyViewHolder> {

    private List<StyleStudioDatum> reviewModelList;
    private Context context;

    public StylesStudioAdapter(List<StyleStudioDatum> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_style_studio, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvName.setText(reviewModelList.get(position).getPrdctName());
        holder.tvCredit.setText("Credit:" + " " + reviewModelList.get(position).getPrdctCredit());
        Picasso.with(context)
                .load(Constant.IMAGE1 + reviewModelList.get(position).getPrdctPic())
                .placeholder(R.drawable.default_img)
                .into(holder.offer_img);

        holder.getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUrl = reviewModelList.get(position).getPrdctLink();
                if (!strUrl.startsWith("http://") && !strUrl.startsWith("https://"))
                    strUrl = "http://" + strUrl;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img;
        private TextView tvName, tvCredit;
        private CardView getBtn;

        public MyViewHolder(View view) {
            super(view);
            offer_img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.tvName);
            tvCredit = view.findViewById(R.id.tvCredit);
            getBtn = view.findViewById(R.id.getBtn);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
