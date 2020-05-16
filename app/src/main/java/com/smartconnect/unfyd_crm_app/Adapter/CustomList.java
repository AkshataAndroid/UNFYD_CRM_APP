package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartconnect.unfyd_crm_app.R;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> {

private ArrayList<String > web;
        LayoutInflater mInflater;
     TextView timeText;
        public CustomList(Context context, ArrayList<String> web) {
        super(context, 0, web);
        mInflater= LayoutInflater.from(context);
        this.web = web;

        }
@Override
public View getView(int position, View view, ViewGroup parent) {

        View rowView= mInflater.inflate(R.layout.list_item,parent,false);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.comment);
        timeText=rowView.findViewById(R.id.time);
        txtTitle.setText(web.get(position));
        return rowView;
        }
        }