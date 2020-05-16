package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartconnect.unfyd_crm_app.Model.OpportunityList;
import com.smartconnect.unfyd_crm_app.Model.TaskPendingmodel;
import com.smartconnect.unfyd_crm_app.R;

import java.util.ArrayList;

public class TaskPendingAdapter extends ArrayAdapter<TaskPendingmodel> {

    Context context;
    ArrayList<TaskPendingmodel> taskPending;
    LayoutInflater inflater;
    TextView subjecttext,descriptiontext,datetext,assigntext,totaltext,titletext;
    public TaskPendingAdapter(Context context, ArrayList<TaskPendingmodel> pending) {
        super(context,0,pending);
        this.context = context;
        this.taskPending = pending;// save list of stores
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// save inflater layout

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // TODO Auto-generated method stub
        TaskPendingmodel taskPendingmodel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_opportunity, parent, false);
        }
        // Lookup view for data population
        subjecttext = (TextView) view.findViewById(R.id.subjecttext);
        descriptiontext=view.findViewById(R.id.titletext);
        datetext=view.findViewById(R.id.time);
        assigntext=view.findViewById(R.id.name);
        totaltext=view.findViewById(R.id.circular);
        titletext=view.findViewById(R.id.txtopportunity);
        assert taskPendingmodel != null;
        subjecttext.setText(taskPendingmodel.getSubjectpending());
        descriptiontext.setText(taskPendingmodel.getDescriptionpending());
        datetext.setText(taskPendingmodel.getDatepending());
        assigntext.setText(taskPendingmodel.getNamepending());
        int total=taskPending.size();
        int positionitem=taskPending.indexOf(taskPendingmodel);

        if(positionitem==0){
            totaltext.setVisibility(View.VISIBLE);
            titletext.setVisibility(View.VISIBLE);

        }else{
            totaltext.setVisibility(View.GONE);
            titletext.setVisibility(View.GONE);
        }
       String totalpending=String.valueOf(total);
       totaltext.setText(totalpending);



        return view;
    }
}