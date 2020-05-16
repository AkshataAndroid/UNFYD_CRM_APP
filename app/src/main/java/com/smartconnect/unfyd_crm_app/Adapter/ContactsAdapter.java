package com.smartconnect.unfyd_crm_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartconnect.unfyd_crm_app.Fragments.ContactsFragment;
import com.smartconnect.unfyd_crm_app.Model.ContactList;
import com.smartconnect.unfyd_crm_app.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends ArrayAdapter<ContactList> {
    Context context;
    ArrayList<ContactList> contactLists;
    LayoutInflater inflater;


    public ContactsAdapter(Context context, ArrayList<ContactList> users) {
        super(context,0,users);
        this.context = context;
        this.contactLists = users;// save list of stores
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// save inflater layout

    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ContactList contactList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_contacts, parent, false);
        }
        // Lookup view for data population
        TextView txtViewTitle = (TextView) convertView.findViewById(R.id.fullname);
        TextView txtViewDescription = (TextView) convertView.findViewById(R.id.phonenumber);
        // Populate the data into the template view using the data object
        assert contactList != null;
        txtViewTitle.setText(contactList.getName());
        txtViewDescription.setText(contactList.getNumber());
        // Return the completed view to render on screen
        return convertView;



    }
    public void updateList(ArrayList<ContactList> mChatDetails) {
        mChatDetails.clear();
        mChatDetails.addAll(mChatDetails);
    }
    private class ViewHolder {
        TextView txtViewTitle;
        TextView txtViewDescription;
    }


}
