package grabbuddy.infobite.grabbuddy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import grabbuddy.infobite.grabbuddy.R;

public class CardViewAdapter extends BaseAdapter {

    private int[] resIds = {};

    public CardViewAdapter(int[] resIds) {
        this.resIds = resIds;
    }

    @Override
    public int getCount() {
        return resIds.length;
    }

    @Override
    public Integer getItem(int position) {
        return resIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card_item, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(resIds[position]);
        return convertView;
    }
}
