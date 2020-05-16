package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartconnect.unfyd_crm_app.Fragments.OpportunitiesFragment;
import com.smartconnect.unfyd_crm_app.Model.ContactList;
import com.smartconnect.unfyd_crm_app.Model.OpportunityList;
import com.smartconnect.unfyd_crm_app.R;

import java.util.ArrayList;

public class OpportunitiesAdapter extends ArrayAdapter<OpportunityList> {
    Context context;
    ArrayList<OpportunityList> opportunityLists;
    LayoutInflater inflater;
    TextView nametext,flagtext,datetext,opportunitytext,nextsteptext;
    public OpportunitiesAdapter(Context context, ArrayList<OpportunityList> oppo) {
        super(context,0,oppo);
        this.context = context;
        this.opportunityLists = oppo;// save list of stores
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// save inflater layout


    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        OpportunityList opportunityList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        // Lookup view for data population
        nametext = (TextView) convertView.findViewById(R.id.name);
        flagtext = (TextView) convertView.findViewById(R.id.flagtext);
        opportunitytext = (TextView) convertView.findViewById(R.id.title);
        datetext = (TextView) convertView.findViewById(R.id.time);
        nextsteptext = (TextView) convertView.findViewById(R.id.follow);
        // Populate the data into the template view using the data object
        assert opportunityList != null;
        nametext.setText(opportunityList.getContactname());
        flagtext.setText(opportunityList.getFlag());
        opportunitytext.setText(opportunityList.getOpportunity());
        datetext.setText(opportunityList.getReminderdate());
        nextsteptext.setText(opportunityList.getNextstep());
        // Return the completed view to render on screen
        return convertView;



    }
//    public void updateList(ArrayList<ContactList> mChatDetails) {
//        mChatDetails.clear();
//        mChatDetails.addAll(mChatDetails);
//    }
//    private class ViewHolder {
//        TextView txtViewTitle;
//        TextView txtViewDescription;
//    }
}