package grabbuddy.infobite.grabbuddy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.modal.api_model.Datum;
import grabbuddy.infobite.grabbuddy.modal.search_modal_data.SearchStoreDatum;

public class CustomerListAdapter extends ArrayAdapter<SearchStoreDatum> {

    private List<SearchStoreDatum> originalList;
    private List<SearchStoreDatum> DatumList;
    private DatumFilter filter;
    private Context context;

    public CustomerListAdapter(Context context, int textViewResourceId, List<SearchStoreDatum> DatumList) {
        super(context, textViewResourceId, DatumList);
        this.DatumList = new ArrayList<SearchStoreDatum>();
        this.DatumList=(DatumList);
        this.originalList = new ArrayList<SearchStoreDatum>();
        this.originalList=(DatumList);
        this.context = context;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new DatumFilter();
        }
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (vi != null) {
                convertView = vi.inflate(R.layout.row_top_stores, null);
            }
            holder = new ViewHolder();
            if (convertView != null) {
                holder.txtCityList = (TextView) convertView.findViewById(R.id.tvName);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       SearchStoreDatum datum = DatumList.get(position);
       holder.txtCityList.setText(datum.getCompanyName());
       Picasso.with(context).load(Constant.IMAGE + datum.getCompanyLogo()).into(holder.img);

        return convertView;
    }

    private class ViewHolder {
        TextView txtCityList;
        ImageView img;
    }

    private class DatumFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<SearchStoreDatum> filteredItems = new ArrayList<SearchStoreDatum>();

                for (int i = 0, l = originalList.size(); i < l; i++)
                // for(int i = 0; i< originalList.size(); i++)
                {
                    SearchStoreDatum datum = originalList.get(i);
                    if (datum.getCompanyName().toLowerCase().contains(constraint))
                        filteredItems.add(datum);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            DatumList = (ArrayList<SearchStoreDatum>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = DatumList.size(); i < l; i++)
                add(DatumList.get(i));
            notifyDataSetInvalidated();
        }
    }
}
