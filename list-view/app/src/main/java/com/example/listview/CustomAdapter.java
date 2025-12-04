package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<CityData.CityInfo> datas;

    public CustomAdapter(Context context,  List<CityData.CityInfo> objects) {
        this.context = context;
        this.datas = objects;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.city_with_image, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.city_image);
        TextView textView = convertView.findViewById(R.id.city_name);

        CityData.CityInfo cityInfo = datas.get(position);
        imageView.setImageResource(cityInfo.drawableId);

        textView.setText(cityInfo.name);

        return convertView;
    }
}
