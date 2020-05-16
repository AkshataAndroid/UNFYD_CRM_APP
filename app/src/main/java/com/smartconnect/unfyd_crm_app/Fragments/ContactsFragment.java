package com.smartconnect.unfyd_crm_app.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartconnect.unfyd_crm_app.Activity.AddContactsActivity;
import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.Adapter.ContactsAdapter;
import com.smartconnect.unfyd_crm_app.Model.ContactList;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.utils.MyApplication;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    View view;
    ListView contactslist;
    //    ContactsAdapter conAdapter;
    FloatingActionButton addcontactButton;
    // ContactList contactList;
    String fullname,phonenumber;
    Context context;

    ContactsAdapter adapter;
    MyApplication myAppClass;
    ContactList  newUser;
    // BottomNavigationView bottomNavigationView;

     ArrayList<ContactList> listcontact = new ArrayList<ContactList>();

    ArrayList<ContactList> arrayOfUsers = new ArrayList<ContactList>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Bundle bun=savedInstanceState;
        try {
            view = inflater.inflate(R.layout.fragment_contacts, container, false);
            SQLiteDatabase.loadLibs(getContext());
            context = getActivity();
            contactslist = view.findViewById(R.id.contactslist);
            addcontactButton = view.findViewById(R.id.addcon);
            FirstActivity activity = (FirstActivity) getActivity();
            if (activity != null)
                activity.hideBottomBar(false);



            if (getArguments() != null) {
                fullname = this.getArguments().getString("Fullname");
                Log.d("Name:", fullname);
                phonenumber = this.getArguments().getString("Number");
                Log.d("Number ", phonenumber);
                   // myAppClass =(MyApplication) getActivity().getApplicationContext();
                  newUser = new ContactList(fullname, phonenumber);
               // ArrayList<ContactList> list= activity.contact(newUser);
                listcontact=activity.contact(newUser);
               // myAppClass.setGlobalArrayList(arrayOfUsers);// Create the adapter to convert the array to views
                adapter = new ContactsAdapter(getContext(), listcontact);
                // adapter.add(newUser);
                adapter.notifyDataSetChanged();
                contactslist.setSaveFromParentEnabled(false);

                contactslist.setAdapter(adapter);


            }else{
                ContactList contactList=null;

                listcontact=activity.contact(contactList);
                adapter=new ContactsAdapter(getContext(),listcontact);
                adapter.notifyDataSetChanged();
                contactslist.setSaveFromParentEnabled(false);

                contactslist.setAdapter(adapter);


            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }



//        List<ContactList> res = new ArrayList<ContactList>();
//        ContactList contacts = new ContactList(fullname, phonenumber);
//        res.add(contacts);
//        conAdapter = new ContactsAdapter(getContext(),contactlist);
//        contactslist.setAdapter(conAdapter);
//        contactlist.add(new ContactList(fullname,phonenumber));
//        conAdapter.notifyDataSetChanged();

//        String[] values = new String[arrayOfUsers.size()];
//        int index = 0;
//        for (Object s : arrayOfUsers) {
//            values[index] = s.toString();
//            index++;
//            adapter.add((ContactList) s);
//        }



        //  contactslist.invalidateViews();


        addcontactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(getActivity(), AddContactsActivity.class);
//
//                //i.putExtra("contactpagefrag", this);
//                startActivity(i);
                Fragment addContactsFragment = new AddContactsFragment();
                //   bottomNavigationView.setVisibility(View.GONE);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contact_container, addContactsFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        try{
            if(arrayOfUsers!=null &&arrayOfUsers.size()>0) {
                adapter = new ContactsAdapter(getContext(),arrayOfUsers);
               contactslist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        @Nullable
//        @Override
//        public FragmentManager getChildFragmentManager(Fragment fragment) {
//            return fragment.getChildFragmentManager();
//        }

      //  adapter.notifyDataSetChanged();



    }

}



