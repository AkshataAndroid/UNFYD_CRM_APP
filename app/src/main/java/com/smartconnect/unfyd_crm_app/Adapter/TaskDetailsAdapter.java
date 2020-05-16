package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartconnect.unfyd_crm_app.Model.TaskCurrentModel;
import com.smartconnect.unfyd_crm_app.Model.TaskPendingmodel;
import com.smartconnect.unfyd_crm_app.R;

import java.util.ArrayList;

public class TaskDetailsAdapter extends ArrayAdapter<TaskCurrentModel> {

    Context context;
    ArrayList<TaskCurrentModel> taskcurrent;
    LayoutInflater inflater;
    TextView subjecttextcurrent,descriptiontextcurrent,datetextcurrent,assigntextcurrent,totalcurrenttext,titlecurrent;
    public TaskDetailsAdapter(Context context, ArrayList<TaskCurrentModel> current) {
        super(context,0,current);
        this.context = context;
        this.taskcurrent = current;// save list of stores
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// save inflater layout

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // TODO Auto-generated method stub
        TaskCurrentModel taskCurrentModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_currunttask, parent, false);
        }
        // Lookup view for data population
        subjecttextcurrent = (TextView) view.findViewById(R.id.intrestcurrent);
        descriptiontextcurrent=view.findViewById(R.id.titlecurrent);
        datetextcurrent=view.findViewById(R.id.timecurrent);
        assigntextcurrent=view.findViewById(R.id.namecurrent);
        totalcurrenttext=view.findViewById(R.id.circular);
        titlecurrent=view.findViewById(R.id.txtopportunity);
        assert taskCurrentModel != null;
        subjecttextcurrent.setText(taskCurrentModel.getSubject());
        descriptiontextcurrent.setText(taskCurrentModel.getDescription());
        datetextcurrent.setText(taskCurrentModel.getDate());
        assigntextcurrent.setText(taskCurrentModel.getName());
        int totalcurrent=taskcurrent.size();
        int positionitem=taskcurrent.indexOf(taskCurrentModel);


        if(positionitem==0){
            totalcurrenttext.setVisibility(View.VISIBLE);
            titlecurrent.setVisibility(View.VISIBLE);

        }else{
            totalcurrenttext.setVisibility(View.GONE);
            titlecurrent.setVisibility(View.GONE);
        }
        String totalvalue=String.valueOf(totalcurrent);
        totalcurrenttext.setText(totalvalue);


        return view;
    }
}