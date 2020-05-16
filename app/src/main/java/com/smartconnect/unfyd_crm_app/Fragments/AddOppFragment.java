package com.smartconnect.unfyd_crm_app.Fragments;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.database.CRMdb;


import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddOppFragment  extends Fragment {
    View view;
    Spinner mobileSpinner,groupSpinner,flagSpinner,stageSpinner,ratingSpinner,channelSpinner,sourceSpinner,partnerSpinner;
    EditText contactedit,opportunityedit,desceit,reminderedit,nextstepedit;
    Button backbutton,submitButton;
    String contacttext,opportunity,flag,datetext,nextstep;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_opp, container, false);
        SQLiteDatabase.loadLibs(getContext());

        init();
        spinners();
        onclicks();


        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(true);

        return view;
    }

    private void onclicks() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacttext= contactedit.getText().toString();
                opportunity=opportunityedit.getText().toString();
                flag = flagSpinner.getSelectedItem().toString();
                datetext=reminderedit.getText().toString();
                nextstep=nextstepedit.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("Contactname",contacttext);
                bundle.putString("Opportunity", opportunity);
                bundle.putString("Flag", flag);
                bundle.putString("RemiderDate", datetext);
                bundle.putString("NextStep", nextstep);
                //database
                ContentValues contentValues = new ContentValues();
                // get  & set with contentvalues
                contentValues.put(CRMdb.CONTACTNAME,contacttext );
                contentValues.put(CRMdb.OPPORTUNITY, opportunity);
                contentValues.put(CRMdb.FLAG, flag);
                contentValues.put(CRMdb.REMINDERDATE, datetext);
                contentValues.put(CRMdb.NEXTSTEP, nextstep);

                SQLiteDatabase lb = CRMdb.getInstance(getContext()).getWritableDatabase("Smart@123");
                long row = lb.insert(CRMdb.DATABASE_TABLE, null, contentValues);

//                CRMdb lb = new CRMdb(getContext());
//                long row = lb.insert(contacttext,opportunity,flag,datetext,nextstep);
                if (row > 0) {
                    Toast.makeText(getContext(), "Your data Inserted Successfully....",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Something Wrong...", Toast.LENGTH_SHORT).show();
                }

                OpportunitiesFragment oppFragment = new OpportunitiesFragment();
                oppFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_addopp,oppFragment );
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(false);    // to show the bottom bar when
        // we destroy this fragment
    }

    private void spinners() {
        //mobile
        List<String> mobilespinners = new ArrayList<String>();
        mobilespinners.add("Select");
        mobilespinners.add("Home");
        mobilespinners.add("Personal");

        ArrayAdapter<String> mobileAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, mobilespinners);
        mobileAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        mobileSpinner.setAdapter(mobileAdapter);
        //group
        List<String> groupspinners = new ArrayList<String>();
        groupspinners.add("Select");
        groupspinners.add("Mobility");
        groupspinners.add("Development");


        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, groupspinners);
        groupAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        groupSpinner.setAdapter(groupAdapter);

        //flag

        List<String> flagspinners = new ArrayList<String>();
        flagspinners.add("Select");
        flagspinners.add("High");
        flagspinners.add("Low");

        ArrayAdapter<String> flagAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, flagspinners);
        flagAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        flagSpinner.setAdapter(flagAdapter);

        //stage
        List<String> stagespinner = new ArrayList<String>();
        stagespinner.add("Select");
        stagespinner.add("Design");
        stagespinner.add("Development");
        stagespinner.add("Testing");


        ArrayAdapter<String> stageAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, stagespinner);
        stageAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        stageSpinner.setAdapter(stageAdapter);

        //rating
        List<String> ratingspinners = new ArrayList<String>();
        ratingspinners.add("Select");
        ratingspinners.add("Good");
        ratingspinners.add("Not good");
        ratingspinners.add("Verygood");

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, ratingspinners);
        ratingAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        ratingSpinner.setAdapter(ratingAdapter);

        //channel
        List<String> channelsspinners = new ArrayList<String>();
        channelsspinners.add("Select");
        channelsspinners.add("channel1");
        channelsspinners.add("channel2");
        channelsspinners.add("channel3");

        ArrayAdapter<String> channelAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, channelsspinners);
        channelAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        channelSpinner.setAdapter(channelAdapter);

        //source
        List<String> sourcespinners = new ArrayList<String>();
        sourcespinners.add("Select");
        sourcespinners.add("channel1");
        sourcespinners.add("channel2");
        sourcespinners.add("channel3");

        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, sourcespinners);
        sourceAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        sourceSpinner.setAdapter(sourceAdapter);

        //partner
        List<String> partnerspinners = new ArrayList<String>();
        partnerspinners.add("Select");
        partnerspinners.add("partner1");
        partnerspinners.add("partner2");
        partnerspinners.add("partner3");

        ArrayAdapter<String> partnerAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, partnerspinners);
        partnerAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        partnerSpinner.setAdapter(partnerAdapter);



    }

    private void init() {
        mobileSpinner=view.findViewById(R.id.mobilespinner);
        groupSpinner=view.findViewById(R.id.groupspinner);
        flagSpinner=view.findViewById(R.id.flagspinner);
        stageSpinner=view.findViewById(R.id.stagespinner);
        ratingSpinner=view.findViewById(R.id.ratingspinner);
        channelSpinner=view.findViewById(R.id.leadspinner);
        sourceSpinner=view.findViewById(R.id.sourcespinner);
        partnerSpinner=view.findViewById(R.id.partnerspinner);
        backbutton=view.findViewById(R.id.backbuttonthree);
        submitButton=view.findViewById(R.id.submitbuttonthree);
        contactedit=view.findViewById(R.id.addcontact);
        opportunityedit=view.findViewById(R.id.addopport);
        reminderedit=view.findViewById(R.id.reminderedit);
        nextstepedit=view.findViewById(R.id.nextedit);
    }
}

