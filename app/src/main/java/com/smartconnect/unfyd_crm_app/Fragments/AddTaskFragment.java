package com.smartconnect.unfyd_crm_app.Fragments;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.database.CRMdb;

import net.sqlcipher.database.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTaskFragment extends Fragment {
    View view;
    ImageView calenderimage;
    TextView selectdatetext;
    Spinner assignspinner,priorityspinner,typespinner,contactsspinner,opportunityspinner,statusspinner;
    Button backbutton,submitbutton;
    Calendar oppcalender;
    String status,opportunity,contact,priority,assign,type,subject,desciption,datetext;
    EditText mSubject,mDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_tasks, container, false);
        SQLiteDatabase.loadLibs(getContext());
        oppcalender = Calendar.getInstance();

        init();
        //visiblity();
        spinners();
        onclicks();
        getSpinnertext();
        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(true);
       // cotactsdb = new DatabaseHelper(getActivity());



        return view;
    }

    private void getSpinnertext() {
        // status = statusspinner.getSelectedItem().toString();
         opportunity = opportunityspinner.getSelectedItem().toString();
        contact = contactsspinner.getSelectedItem().toString();
        priority = priorityspinner.getSelectedItem().toString();
        // assign = assignspinner.getSelectedItem().toString();
         type = typespinner.getSelectedItem().toString();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(false);    // to show the bottom bar when
        // we destroy this fragment
    }

    private void onclicks() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                oppcalender.set(Calendar.YEAR, year);
                oppcalender.set(Calendar.MONTH, monthOfYear);
                oppcalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        calenderimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, oppcalender
                        .get(Calendar.YEAR), oppcalender.get(Calendar.MONTH),
                        oppcalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject= mSubject.getText().toString();
                desciption=mDescription.getText().toString();
                datetext = selectdatetext.getText().toString();
                assign = assignspinner.getSelectedItem().toString();
                status=statusspinner.getSelectedItem().toString();

                //database
                ContentValues contentValues = new ContentValues();
                // get  & set with contentvalues
                contentValues.put(CRMdb.SUBJECT,subject );
                contentValues.put(CRMdb.DESCRIPION, desciption);
                contentValues.put(CRMdb.DATE, datetext);
                contentValues.put(CRMdb.ASSIGN, assign);
                contentValues.put(CRMdb.STATUS, status);



                CRMdb lb = new CRMdb(getContext());
                long row = lb.addtaskItem(subject,desciption,datetext,assign,status);
                if (row > 0) {
                    Toast.makeText(getContext(), "Your data Inserted Successfully....",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Something Wrong...", Toast.LENGTH_SHORT).show();
                }

                Bundle bundle = new Bundle();
                bundle.putString("Subject",subject);
                bundle.putString("Description", desciption);
                bundle.putString("Date", datetext);
                bundle.putString("Assign", assign);
                bundle.putString("Status", status);

                TasksFragment tasksFragment = new TasksFragment();
                tasksFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_addtasks,tasksFragment );
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
    private void updateLabel() {
        String myFormat = "dd MMM YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        selectdatetext.setText(sdf.format(oppcalender.getTime()));
    }
    private void spinners() {
        //assign
        List<String> assignspinners = new ArrayList<String>();
        assignspinners.add("Select");
        assignspinners.add("Akshata G");
        assignspinners.add("Nikita P");
        assignspinners.add("Pranali K");
        assignspinners.add("Amruta P");

        ArrayAdapter<String> assignAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, assignspinners);
        assignAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        assignspinner.setAdapter(assignAdapter);
        //priority
        List<String> priorityspinners = new ArrayList<String>();
        priorityspinners.add("Select");
        priorityspinners.add("High");
        priorityspinners.add("Low");
        priorityspinners.add("Medium");

        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, priorityspinners);
        priorityAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        priorityspinner.setAdapter(priorityAdapter);

        //type

        List<String> typespinners = new ArrayList<String>();
        typespinners.add("Select");
        typespinners.add("English");
        typespinners.add("Hindi");
        typespinners.add("Marathi");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, typespinners);
        typeAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        typespinner.setAdapter(typeAdapter);

        //cotacts
        List<String> contactspinners = new ArrayList<String>();
        contactspinners.add("Select");
        contactspinners.add("English");
        contactspinners.add("Hindi");
        contactspinners.add("Marathi");

        ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, contactspinners);
        contactAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        contactsspinner.setAdapter(contactAdapter);

        //opportunity
        List<String> opporunityspinners = new ArrayList<String>();
        opporunityspinners.add("Select");
        opporunityspinners.add("English");
        opporunityspinners.add("Hindi");
        opporunityspinners.add("Marathi");

        ArrayAdapter<String> oppAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, opporunityspinners);
        oppAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        opportunityspinner.setAdapter(oppAdapter);

        //status
        List<String> statusspinners = new ArrayList<String>();
        statusspinners.add("Select");
        statusspinners.add("Current");
        statusspinners.add("Pending");


        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, statusspinners);
        statusAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        statusspinner.setAdapter(statusAdapter);
    }

    private void init() {
        calenderimage=view.findViewById(R.id.calenderimagetwo);
        selectdatetext=view.findViewById(R.id.selectdatetexttwo);
        assignspinner=view.findViewById(R.id.assignspinner);
        priorityspinner=view.findViewById(R.id.priorityspinner);
        typespinner=view.findViewById(R.id.typespinner);
        contactsspinner=view.findViewById(R.id.contactspinner);
        opportunityspinner=view.findViewById(R.id.opporspinner);
        statusspinner=view.findViewById(R.id.statusspinner);
        backbutton=view.findViewById(R.id.backbuttontwo);
        submitbutton=view.findViewById(R.id.submitbuttontwo);
        mDescription=view.findViewById(R.id.addDescription);
        mSubject=view.findViewById(R.id.addSubject);

    }
}
