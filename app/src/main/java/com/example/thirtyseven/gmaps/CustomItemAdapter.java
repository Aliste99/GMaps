package com.example.thirtyseven.gmaps;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class CustomItemAdapter extends ArrayAdapter<Flat> {

    private Context context;

    public CustomItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList flat) {
        super(context, resource, flat);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.custom_item, parent, false);
        }
        Flat flat = getItem(position);

        TextView address = (TextView) listViewItem.findViewById(R.id.adrTextView);
        TextView area = (TextView) listViewItem.findViewById(R.id.areaTextView);
        TextView roomCount = (TextView) listViewItem.findViewById(R.id.roomCountTextView);
        TextView description = (TextView) listViewItem.findViewById(R.id.descrTextView);
        TextView price = (TextView) listViewItem.findViewById(R.id.priceTextView);




        address.setTextSize(23);
        address.setText(String.valueOf(flat.getAddress()));
        area.setText(String.valueOf(flat.getArea()));
        roomCount.setText(String.valueOf(flat.getRoomCount()));
        description.setText(String.valueOf(flat.getDescription()));
        price.setText(String.valueOf(flat.getPrice()));



        return listViewItem;
    }
}
