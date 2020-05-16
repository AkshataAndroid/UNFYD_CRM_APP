package com.smartconnect.unfyd_crm_app.Fragments;

import android.content.ContentValues;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.Adapter.ContactsAdapter;
import com.smartconnect.unfyd_crm_app.Adapter.CustomList;
import com.smartconnect.unfyd_crm_app.Adapter.OpportunitiesAdapter;
import com.smartconnect.unfyd_crm_app.Model.ContactList;
import com.smartconnect.unfyd_crm_app.Model.OpportunityList;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.database.CRMdb;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class OpportunitiesFragment extends Fragment {
    View view;
    ListView opportunityslist;
    OpportunitiesAdapter OpAdapter;
    OpportunityList newOpportunity;
    ArrayList<OpportunityList> listopportunity= new ArrayList<OpportunityList>();
    ArrayList<OpportunityList> arrayOfOpportunity = new ArrayList<OpportunityList>();
    FloatingActionButton addoppButton;
    String contactname,opportunity,flag,remindeDate,nextstep,total;
    TextView totaltext;
    int  totalaopportunity;


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        final Bundle bun=savedInstanceState;

        try{
//            if(CRMdb.enableSQLCypher)
//            {
//                SQLiteDatabase.loadLibs(getContext());
//            }

        view = inflater.inflate(R.layout.fragment_opportunities, container, false);
            SQLiteDatabase.loadLibs(getContext());
        opportunityslist = view.findViewById(R.id.opportunityslist);
        addoppButton = view.findViewById(R.id.addopp);
        totaltext=view.findViewById(R.id.circular);
        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(false);

            if (getArguments() != null) {
                contactname = this.getArguments().getString("Contactname");
                Log.d("Name:", contactname);
                opportunity = this.getArguments().getString("Opportunity");
                Log.d("Opportunity ", opportunity);
                flag = this.getArguments().getString("Flag");
                Log.d("Flag:", flag);
                remindeDate = this.getArguments().getString("RemiderDate");
                Log.d("RemiderDate ", remindeDate);
                nextstep = this.getArguments().getString("NextStep");
                Log.d("NextStep ", nextstep);



                newOpportunity = new OpportunityList(contactname, opportunity,flag,remindeDate,nextstep);
                listopportunity=activity.opportunity(newOpportunity);
                OpAdapter = new OpportunitiesAdapter(getContext(), listopportunity);
                OpAdapter.notifyDataSetChanged();
                opportunityslist.setSaveFromParentEnabled(false);
                opportunityslist.setAdapter(OpAdapter);
                 totalaopportunity=opportunityslist.getAdapter().getCount();
                total=String.valueOf(totalaopportunity);
                totaltext.setText(total);


            }else{
                OpportunityList opportunityList=null;
                listopportunity=activity.opportunity(opportunityList);
                OpAdapter = new OpportunitiesAdapter(getContext(), listopportunity);
                OpAdapter.notifyDataSetChanged();
                opportunityslist.setSaveFromParentEnabled(false);
                opportunityslist.setAdapter(OpAdapter);
                  totalaopportunity=opportunityslist.getAdapter().getCount();
                total=String.valueOf(totalaopportunity);
                totaltext.setText(total);



            }



//        OpAdapter = new OpportunitiesAdapter(getContext(),opportunitylist);
//        opportunityslist.setAdapter(OpAdapter);
//        opportunitylist.add("");
//        OpAdapter.notifyDataSetChanged();
        }catch (Exception ex){
            ex.printStackTrace();

        }

        addoppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addOppFragment = new AddOppFragment();

                addoppButton.setVisibility(View.GONE);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.opp_container, addOppFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        try{
            if(arrayOfOpportunity!=null &&arrayOfOpportunity.size()>0) {
                OpAdapter = new OpportunitiesAdapter(getContext(),arrayOfOpportunity);
                opportunityslist.setAdapter(OpAdapter);
                OpAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
