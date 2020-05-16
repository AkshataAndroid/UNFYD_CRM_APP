package com.smartconnect.unfyd_crm_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartconnect.unfyd_crm_app.Fragments.ContactsFragment;
import com.smartconnect.unfyd_crm_app.Fragments.OpportunitiesFragment;
import com.smartconnect.unfyd_crm_app.Fragments.TasksFragment;
import com.smartconnect.unfyd_crm_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddContactsActivity extends AppCompatActivity {
    EditText mWhatsappno,mPhoneNumber,mFullName,mEmailpersonal,mEmailoffice,mSalutation,mFirstname,mLastname,mAddFullAddress;
    TextView mName ,mPhone,viewmore,mEmail,mSocial,infotext,mLanguage,mGender,mDesignation,mIndustry,mGtm,mDuedate,mOrganisation
            ,mSelectDate,mAddress,mCountry,mState,mCity,mZipcode,mFullAddress;
    RelativeLayout namepanal,phonepanal,onlynamePanal,emailpanal,socialpanal;
    ImageView uparrow,plusimage,whatsminus,downarrow,plusEmailimage,moreimage,socialimage,plusSocial,infoimage,infodown,upcontact,downcontact,calenderimage,infoaddress,upaddress,downaddress,importicon;
    Spinner phoneSpinner,whatsAppSpinner,emailSpinner,emailtwoSpinner,langspinner,genderspinner,Designationspinner,
            industryspinner,gtmspinner,organisationspinner,countryspinner,statespinner,cityspinner,zipcodespinner;
    CheckBox checkBox;
    View nameview,phoneview,emailview,socialview,langspinnerview,dateview,generview,languageview,designationview,industryview
            ,gtmview,orgview,contryview,stateview,cityview,zipcodeview,fulladdressview;
    Context context;
    final int  MY_PERMISSIONS_REQUEST_READ_CONTACTS=1;
    String fullname,number;
    Calendar myCalendar;
    Button backbutton,submitButton;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        myCalendar = Calendar.getInstance();
        bottomNavigationView= findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        init();
        visiblity();
        spinners();



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname= mFullName.getText().toString();
                number=mPhoneNumber.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("Fullname",fullname);
                bundle.putString("Number", number);
                ContactsFragment contactsFragment = new ContactsFragment();
                contactsFragment.setArguments(bundle);
                bottomNavigationView.setVisibility(View.VISIBLE);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,contactsFragment );
              //  transaction.addToBackStack(ContactsFragment.class);
               // transaction.disallowAddToBackStack();
                //transaction.remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
                transaction.commit();


            }

//            private void replaceFragment(ContactsFragment contactsFragment) {
//                       ContactsFragment newCase=new ContactsFragment();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container,newCase); // give your fragment container id in first parameter
//        transaction.commit();
//            }
        });
backbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       Intent i=new Intent(AddContactsActivity.this,FirstActivity.class);
       startActivity(i);
//        Fragment newCase=new ContactsFragment();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content,newCase); // give your fragment container id in first parameter
//        transaction.commit();


    }
});
        calenderimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddContactsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        importicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (ContextCompat.checkSelfPermission(AddContactsActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(AddContactsActivity.this,
                            Manifest.permission.READ_CONTACTS)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddContactsActivity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);

            }
        });
        upcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGender.setVisibility(View.GONE);
                mDesignation.setVisibility(View.GONE);
                mIndustry.setVisibility(View.GONE);
                mGtm.setVisibility(View.GONE);
                mDuedate.setVisibility(View.GONE);
                mOrganisation.setVisibility(View.GONE);
                mSelectDate.setVisibility(View.GONE);
                genderspinner.setVisibility(View.GONE);
                Designationspinner.setVisibility(View.GONE);
                industryspinner.setVisibility(View.GONE);
                gtmspinner.setVisibility(View.GONE);
                organisationspinner.setVisibility(View.GONE);
                dateview.setVisibility(View.GONE);
                calenderimage.setVisibility(View.GONE);
                generview.setVisibility(View.GONE);
                languageview.setVisibility(View.VISIBLE);
                designationview.setVisibility(View.GONE);
                industryview.setVisibility(View.GONE);
                gtmview.setVisibility(View.GONE);
                orgview.setVisibility(View.GONE);
                downcontact.setVisibility(View.VISIBLE);
                upcontact.setVisibility(View.GONE);

            }
        });
        upaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mState.setVisibility(View.GONE);
                mCity.setVisibility(View.GONE);
                mZipcode.setVisibility(View.GONE);
                mFullAddress.setVisibility(View.GONE);
                contryview.setVisibility(View.VISIBLE);
                stateview.setVisibility(View.GONE);
                cityview.setVisibility(View.GONE);
                zipcodeview.setVisibility(View.GONE);
                mAddFullAddress.setVisibility(View.GONE);
                countryspinner.setVisibility(View.VISIBLE);
                statespinner.setVisibility(View.GONE);
                cityspinner.setVisibility(View.GONE);
                zipcodespinner.setVisibility(View.GONE);
                downaddress.setVisibility(View.VISIBLE);
                upaddress.setVisibility(View.GONE);

            }
        });
        downcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upcontact.setVisibility(View.VISIBLE);
                downcontact.setVisibility(View.GONE);
                mGender.setVisibility(View.VISIBLE);
                mDesignation.setVisibility(View.VISIBLE);
                mIndustry.setVisibility(View.VISIBLE);
                mGtm.setVisibility(View.VISIBLE);
                mDuedate.setVisibility(View.VISIBLE);
                mOrganisation.setVisibility(View.VISIBLE);
                mSelectDate.setVisibility(View.VISIBLE);
                genderspinner.setVisibility(View.VISIBLE);
                Designationspinner.setVisibility(View.VISIBLE);
                industryspinner.setVisibility(View.VISIBLE);
                gtmspinner.setVisibility(View.VISIBLE);
                organisationspinner.setVisibility(View.VISIBLE);
                dateview.setVisibility(View.VISIBLE);
                calenderimage.setVisibility(View.VISIBLE);
                generview.setVisibility(View.VISIBLE);
                languageview.setVisibility(View.VISIBLE);
                designationview.setVisibility(View.VISIBLE);
                industryview.setVisibility(View.VISIBLE);
                gtmview.setVisibility(View.VISIBLE);
                orgview.setVisibility(View.VISIBLE);
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailpanal.setVisibility(View.VISIBLE);
                plusEmailimage.setVisibility(View.VISIBLE);
                emailview.setVisibility(View.GONE);
            }
        });
        plusEmailimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailpanal.setVisibility(View.GONE);
                plusEmailimage.setVisibility(View.GONE);
                emailview.setVisibility(View.VISIBLE);


            }
        });
        viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreimage.setVisibility(View.GONE);
                viewmore.setVisibility(View.GONE);
                socialimage.setVisibility(View.VISIBLE);
                socialview.setVisibility(View.VISIBLE);
                mSocial.setVisibility(View.VISIBLE);
                plusSocial.setVisibility(View.GONE);
                infodown.setVisibility(View.VISIBLE);
                infoimage.setVisibility(View.VISIBLE);
                infotext.setVisibility(View.VISIBLE);
                langspinner.setVisibility(View.VISIBLE);
                mLanguage.setVisibility(View.VISIBLE);
                langspinnerview.setVisibility(View.VISIBLE);
                infoaddress.setVisibility(View.VISIBLE);

                downaddress.setVisibility(View.VISIBLE);
                mAddress.setVisibility(View.VISIBLE);
                mCountry.setVisibility(View.VISIBLE);

                contryview.setVisibility(View.VISIBLE);

                countryspinner.setVisibility(View.VISIBLE);



            }
        });
        downaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downaddress.setVisibility(View.GONE);
                upaddress.setVisibility(View.VISIBLE);

                mState.setVisibility(View.VISIBLE);
                mCity.setVisibility(View.VISIBLE);
                mZipcode.setVisibility(View.VISIBLE);
                mFullAddress.setVisibility(View.VISIBLE);
                stateview.setVisibility(View.VISIBLE);
                cityview.setVisibility(View.VISIBLE);
                zipcodeview.setVisibility(View.VISIBLE);
                mAddFullAddress.setVisibility(View.VISIBLE);
                statespinner.setVisibility(View.VISIBLE);
                cityspinner.setVisibility(View.VISIBLE);
                zipcodespinner.setVisibility(View.VISIBLE);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    whatsminus.setVisibility(View.GONE);
                    mWhatsappno.setVisibility(View.GONE);
                    whatsAppSpinner.setVisibility(View.GONE);

                }else{
                    whatsminus.setVisibility(View.VISIBLE);
                    mWhatsappno.setVisibility(View.VISIBLE);
                    whatsAppSpinner.setVisibility(View.VISIBLE);
                }

            }
        });

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlynamePanal.setVisibility(View.VISIBLE);
                nameview.setVisibility(View.GONE);
            }
        });
        downarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namepanal.setVisibility(View.VISIBLE);
                onlynamePanal.setVisibility(View.GONE);
            }
        });
plusSocial.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        socialpanal.setVisibility(View.GONE);
        plusSocial.setVisibility(View.GONE);
        socialview.setVisibility(View.VISIBLE);
    }
});
        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonepanal.setVisibility(View.VISIBLE);
                plusimage.setVisibility(View.VISIBLE);
                phoneview.setVisibility(View.GONE);
            }
        });
        plusimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonepanal.setVisibility(View.GONE);
                plusimage.setVisibility(View.GONE);
                phoneview.setVisibility(View.VISIBLE);
            }
        });
        uparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namepanal.setVisibility(View.GONE);
                nameview.setVisibility(View.VISIBLE);

            }
        });
        mSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusSocial.setVisibility(View.VISIBLE);
                socialpanal.setVisibility(View.VISIBLE);
                socialview.setVisibility(View.GONE);

            }
        });




    }
    private void updateLabel() {
        String myFormat = "dd MMM YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mSelectDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void spinners() {
        //spinnerforphone
        phoneSpinner = (Spinner) findViewById(R.id.phonespinner);
        List<String> list = new ArrayList<String>();
        list.add("Mobile");
        list.add("Landline");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.phone_spinner, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        phoneSpinner.setAdapter(dataAdapter);

        //whatsappspinner
        whatsAppSpinner = (Spinner) findViewById(R.id.whatsapppinner);
        List<String> wlist = new ArrayList<String>();
        wlist.add("WANo1");
        wlist.add("WANo2");

        ArrayAdapter<String> whatsaapAdapter = new ArrayAdapter<String>(this,
                R.layout.phone_spinner, wlist);
        whatsaapAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        whatsAppSpinner.setAdapter(whatsaapAdapter);

        //emailSpinner
        List<String> emaillist = new ArrayList<String>();
        emaillist.add("Work");
        emaillist.add("Personal");

        ArrayAdapter<String> emailAdapter = new ArrayAdapter<String>(this,
                R.layout.phone_spinner, emaillist);
        emailAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        emailSpinner.setAdapter(emailAdapter);

        List<String> emaillisttwo = new ArrayList<String>();
        emaillisttwo.add("Work");
        emaillisttwo.add("Personal");

        ArrayAdapter<String> emailtwoAdapter = new ArrayAdapter<String>(this,
                R.layout.phone_spinner, emaillisttwo);
        emailtwoAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        emailtwoSpinner.setAdapter(emailtwoAdapter);

        //languagespinner
        List<String> languagespinner = new ArrayList<String>();
        languagespinner.add("Select");
        languagespinner.add("English");
        languagespinner.add("Hindi");
        languagespinner.add("Marathi");

        ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, languagespinner);
        languageAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        langspinner.setAdapter(languageAdapter);
//gender
        List<String> genderlist = new ArrayList<String>();
        genderlist.add("Select");
        genderlist.add("Male");
        genderlist.add("Female");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, genderlist);
        genderAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        genderspinner.setAdapter(genderAdapter);

        //designation
        List<String> designationlist = new ArrayList<String>();
        designationlist.add("Select");
        designationlist.add("Associate Software engineer");
        designationlist.add("Software engineer");
        designationlist.add("Senior Software engineer");
        designationlist.add("Assistant Maneger");
        designationlist.add("Vice president");
        designationlist.add("General Maneger");

        ArrayAdapter<String> designationAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, designationlist);
        designationAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        Designationspinner.setAdapter(designationAdapter);

        //Industry type
        List<String> industrylist = new ArrayList<String>();
        industrylist.add("Select");
        industrylist.add("IT");
        industrylist.add("Agricultural");
        industrylist.add("Finance");
        industrylist.add("Accounting");
        industrylist.add("Sales");
        industrylist.add("Media");

        ArrayAdapter<String> industrytypeAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, industrylist);
        industrytypeAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        industryspinner.setAdapter(industrytypeAdapter);

        //gtmpartner
        List<String> gtmpartnerlist = new ArrayList<String>();
        gtmpartnerlist.add("Select");
        gtmpartnerlist.add("Partner1");
        gtmpartnerlist.add("Partner2");


        ArrayAdapter<String> gtmpartnerAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, gtmpartnerlist);
        gtmpartnerAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        gtmspinner.setAdapter(gtmpartnerAdapter);

        //gtmpartner
        List<String> organisationlist = new ArrayList<String>();
        organisationlist.add("Select");
        organisationlist.add("SmartConnect");
        organisationlist.add("UNFYD");
        organisationlist.add("SmartOnApp");

        ArrayAdapter<String> organisationAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, organisationlist);
        organisationAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        organisationspinner.setAdapter(organisationAdapter);

        //Country
        List<String> countrylist = new ArrayList<String>();
        countrylist.add("Select");
        countrylist.add("India");
        countrylist.add("United States");
        countrylist.add("U.K.");
        countrylist.add("Russia");
        countrylist.add("Canada");
        countrylist.add("Nepal");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, countrylist);
        countryAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        countryspinner.setAdapter(countryAdapter);

        //State type
        List<String> statelist = new ArrayList<String>();
        statelist.add("Select");
        statelist.add("Maharashtra");
        statelist.add("Telangana");
        statelist.add("punjab");
        statelist.add("A.P");
        statelist.add("U.P");
        statelist.add("West Bengal");

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, statelist);
        stateAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        statespinner.setAdapter(stateAdapter);

        //City
        List<String> citylist = new ArrayList<String>();
        citylist.add("Select");
        citylist.add("Mumbai");
        citylist.add("Hydrabad");
        citylist.add("Chennai");
        citylist.add("Pune");
        citylist.add("Nagpur");
        citylist.add("Kolkata");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, citylist);
        cityAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        cityspinner.setAdapter(cityAdapter);

        //zipcode
        List<String> ziplistlist = new ArrayList<String>();
        ziplistlist.add("Select");
        ziplistlist.add("400001");
        ziplistlist.add("500001");
        ziplistlist.add("600001");
        ziplistlist.add("411000");


        ArrayAdapter<String> zipcodeAdapter = new ArrayAdapter<String>(this,
                R.layout.language_spinner, ziplistlist);
        zipcodeAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        zipcodespinner.setAdapter(zipcodeAdapter);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            Fragment fragment = null;
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            switch (item.getItemId()) {
                case R.id.navigation_tasks:
                    if (!(currentFragment instanceof TasksFragment)) {
                        fragment = new TasksFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, fragment)
                                .commit();


                    }
                    return true;
                case R.id.navigation_contacts:
                    if (!(currentFragment instanceof ContactsFragment)) {
                        fragment = new ContactsFragment();

                    }
                    return true;
                case R.id.navigation_opportunities:
                    fragment = new OpportunitiesFragment();

                    return true;
                case R.id.navigation_settings:
//                    fragment = new DetailsActivity();
//                    loadFragment(fragment);
                    Intent intent = new Intent(AddContactsActivity.this, DetailsActivity.class);
                    startActivity(intent);
                    return true;

            }

            return false;
        }
    };

    private void visiblity() {
        namepanal.setVisibility(View.GONE);
        onlynamePanal.setVisibility(View.GONE);
        phonepanal.setVisibility(View.GONE);
        plusimage.setVisibility(View.GONE);
        emailpanal.setVisibility(View.GONE);
        plusEmailimage.setVisibility(View.GONE);
        socialimage.setVisibility(View.GONE);
        socialview.setVisibility(View.GONE);
        mSocial.setVisibility(View.GONE);
        plusSocial.setVisibility(View.GONE);
        socialpanal.setVisibility(View.GONE);
        infodown.setVisibility(View.GONE);
        infoimage.setVisibility(View.GONE);
        infotext.setVisibility(View.GONE);
        langspinner.setVisibility(View.GONE);
        mLanguage.setVisibility(View.GONE);
        langspinnerview.setVisibility(View.GONE);
        upcontact.setVisibility(View.GONE);
        mGender.setVisibility(View.GONE);
        mDesignation.setVisibility(View.GONE);
        mIndustry.setVisibility(View.GONE);
        mGtm.setVisibility(View.GONE);
        mDuedate.setVisibility(View.GONE);
        mOrganisation.setVisibility(View.GONE);
        mSelectDate.setVisibility(View.GONE);
        genderspinner.setVisibility(View.GONE);
        Designationspinner.setVisibility(View.GONE);
        industryspinner.setVisibility(View.GONE);
        gtmspinner.setVisibility(View.GONE);
        organisationspinner.setVisibility(View.GONE);
        dateview.setVisibility(View.GONE);
        calenderimage.setVisibility(View.GONE);
        generview.setVisibility(View.GONE);
        languageview.setVisibility(View.GONE);
        designationview.setVisibility(View.GONE);
        industryview.setVisibility(View.GONE);
        gtmview.setVisibility(View.GONE);
        orgview.setVisibility(View.GONE);
        infoaddress.setVisibility(View.GONE);
        upaddress.setVisibility(View.GONE);
        downaddress.setVisibility(View.GONE);
        mAddress.setVisibility(View.GONE);
        mCountry.setVisibility(View.GONE);
        mState.setVisibility(View.GONE);
        mCity.setVisibility(View.GONE);
        mZipcode.setVisibility(View.GONE);
        mFullAddress.setVisibility(View.GONE);
        contryview.setVisibility(View.GONE);
        stateview.setVisibility(View.GONE);
        cityview.setVisibility(View.GONE);
        zipcodeview.setVisibility(View.GONE);
        mAddFullAddress.setVisibility(View.GONE);
        countryspinner.setVisibility(View.GONE);
        statespinner.setVisibility(View.GONE);
        cityspinner.setVisibility(View.GONE);
        zipcodespinner.setVisibility(View.GONE);
    }

    private void init() {
        downcontact=findViewById(R.id.downcontact);
        mName=findViewById(R.id.editName);
        submitButton=findViewById(R.id.submitbutton);
        mSalutation=findViewById(R.id.first);
        mFirstname=findViewById(R.id.middle);
        backbutton=findViewById(R.id.backbutton);
        mLastname=findViewById(R.id.last);
        mPhone=findViewById(R.id.editPhone);
        namepanal=findViewById(R.id.namepanal);
        viewmore=findViewById(R.id.viewmore);
        socialpanal=findViewById(R.id.socialpanal);
        mSocial=findViewById(R.id.socialtext);
        socialimage=findViewById(R.id.socialimg);
        socialview=findViewById(R.id.socialview);
        plusSocial=findViewById(R.id.plussocial);
        moreimage=findViewById(R.id.moreimage);
        uparrow=findViewById(R.id.up);
        downarrow=findViewById(R.id.down);
        onlynamePanal=findViewById(R.id.onlynamepanal);
        phonepanal=findViewById(R.id.phonepanal);
        //
        mWhatsappno=findViewById(R.id.whatsappnumber);
        mPhoneNumber=findViewById(R.id.phonenumber);
        mFullName=(EditText) findViewById(R.id.nametext);
        mFullName.getText().clear();
        mEmailpersonal=findViewById(R.id.emailone);
        mEmailoffice=findViewById(R.id.emailtwo);
        whatsminus=findViewById(R.id.minustwo);
        nameview=findViewById(R.id.nameview);
        emailview=findViewById(R.id.emailview);
        //
        phoneview=findViewById(R.id.phoneview);
        plusimage=findViewById(R.id.plusimage);
        checkBox=findViewById(R.id.checkBoxcontact);
        mEmail=findViewById(R.id.editEmail);
        emailSpinner=findViewById(R.id.emailspinner);
        emailtwoSpinner=findViewById(R.id.emailtwospinner);
        plusEmailimage=findViewById(R.id.plusemail);
        emailpanal=findViewById(R.id.emailpanal);
        infodown=findViewById(R.id.downcontact);
        infoimage=findViewById(R.id.infoimage);
        infotext=findViewById(R.id.infotext);
        langspinner=findViewById(R.id.langspinner);
        mLanguage=findViewById(R.id.prelanguage);
        langspinnerview=findViewById(R.id.laguageview);
        upcontact=findViewById(R.id.upcontact);
        mGender=findViewById(R.id.gender);
        mDesignation=findViewById(R.id.designation);
        mIndustry=findViewById(R.id.intype);
        mGtm=findViewById(R.id.gtmpartner);
        mDuedate=findViewById(R.id.date);
        mOrganisation=findViewById(R.id.organizastio);
        mSelectDate=findViewById(R.id.selectdatetext);
        genderspinner=findViewById(R.id.genederspinner);
        Designationspinner=findViewById(R.id.designationspinner);
        industryspinner=findViewById(R.id.industryspinner);
        gtmspinner=findViewById(R.id.gtmspinner);
        organisationspinner=findViewById(R.id.organisationspinner);
        dateview=findViewById(R.id.dateview);
        calenderimage=findViewById(R.id.calenderimage);
        generview=findViewById(R.id.genderview);
        languageview=findViewById(R.id.laguageview);
        designationview=findViewById(R.id.designationview);
        industryview=findViewById(R.id.industryview);
        gtmview=findViewById(R.id.gtmview);
        orgview=findViewById(R.id.orgview);
        infoaddress=findViewById(R.id.infotwoimage);
        upaddress=findViewById(R.id.upaddress);
        downaddress=findViewById(R.id.downaddress);
        mAddress=findViewById(R.id.addresstext);
        mCountry=findViewById(R.id.country);
        mState=findViewById(R.id.state);
        mCity=findViewById(R.id.city);
        mZipcode=findViewById(R.id.zipcode);
        mFullAddress=findViewById(R.id.fulladdress);
        contryview=findViewById(R.id.countryview);
        stateview=findViewById(R.id.stateview);
        cityview=findViewById(R.id.cityview);
        zipcodeview=findViewById(R.id.zipcodeview);
        mAddFullAddress=findViewById(R.id.addfulladdress);
        countryspinner=findViewById(R.id.countryspinner);
        statespinner=findViewById(R.id.statespinner);
        cityspinner=findViewById(R.id.cityspinner);
        zipcodespinner=findViewById(R.id.zipcodespinner);
        importicon=findViewById(R.id.importicon);

    }
        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = ContactsContract.Contacts.CONTENT_URI; // Contact URI
        Cursor contactsCursor = getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "); // Return



        if (contactsCursor.moveToFirst()) {
            do {
                long contctId = contactsCursor.getLong(contactsCursor
                        .getColumnIndex("_ID")); // Get contact ID
                Uri dataUri = ContactsContract.Data.CONTENT_URI; // URI to get
                // data of
                // contacts
                Cursor dataCursor = getContentResolver().query(dataUri, null,
                        ContactsContract.Data.CONTACT_ID + " = " + contctId,
                        null, null);// Retrun data cusror represntative to
                // contact ID

                // Strings to get all details
                String displayName = "";
                String nickName = "";
                String homePhone = "";
                String mobilePhone = "";
                String workPhone = "";
               // String photoPath = "" + R.drawable.ic_launcher; // Photo path
                byte[] photoByte = null;// Byte to get photo since it will come
                // in BLOB
                String homeEmail = "";
                String workEmail = "";
                String companyName = "";
                String title = "";

                // This strings stores all contact numbers, email and other
                // details like nick name, company etc.
                String contactNumbers = "";
                String contactEmailAddresses = "";
                String contactOtherDetails = "";

                // Now start the cusrsor
                if (dataCursor.moveToFirst()) {
                    displayName = dataCursor
                            .getString(dataCursor
                                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));// get
                    String phoneticname = dataCursor.getString(dataCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHONETIC_NAME));





                    // the
                    // contact
                    // name
                    mFullName.setText(displayName);
                  //  pName.setText("Phonetic Name : "+phoneticname);


                    do {
                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
//                            nickName = dataCursor.getString(dataCursor
//                                    .getColumnIndex("data1")); // Get Nick Name
//                            contactOtherDetails += "NickName : " + nickName
                               //     + "\n";// Add the nick name to string
                            String surname = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                            String sufix = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.SUFFIX));
                            String firstname = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                            mSalutation.setText(sufix);
                  mFirstname.setText(firstname);
                   mLastname.setText(surname);


                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {

                            // In this get All contact numbers like home,
                            // mobile, work, etc and add them to numbers string
                            switch (dataCursor.getInt(dataCursor
                                    .getColumnIndex("data2"))) {

                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    homePhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += "Home Phone : " + homePhone
                                            + "\n";
                                    mPhoneNumber.setText(homePhone);
                                    break;

                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    workPhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += "Work Phone : " + workPhone
                                            + "\n";
                                    mWhatsappno.setText(workPhone);
                                    break;

                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    mobilePhone = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactNumbers += "Mobile Phone : "
                                            + mobilePhone + "\n";
                                  //  code.setText("Mobile : "+mobilePhone);
                                    break;

                            }
                        }
                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {

                            // In this get all Emails like home, work etc and
                            // add them to email string
                            switch (dataCursor.getInt(dataCursor
                                    .getColumnIndex("data2"))) {
                                case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                                    homeEmail = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactEmailAddresses += "Home Email : "
                                            + homeEmail + "\n";
                                    mEmailpersonal.setText(homeEmail);
                                    break;
                                case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                                    workEmail = dataCursor.getString(dataCursor
                                            .getColumnIndex("data1"));
                                    contactEmailAddresses += "Work Email : "
                                            + workEmail + "\n";
                                    mEmailoffice.setText(workEmail);


                                    break;

                            }
                        }

                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
                            companyName = dataCursor.getString(dataCursor
                                    .getColumnIndex("data1"));// get company
                            // name
                            contactOtherDetails += "Coompany Name : "
                                    + companyName + "\n";
                            title = dataCursor.getString(dataCursor
                                    .getColumnIndex("data4"));// get Company
                           // state.setText("Company Name : "+companyName);
                            // title
                            contactOtherDetails += "Title : " + title + "\n";

                        }
                        if (dataCursor
                                .getString(
                                        dataCursor.getColumnIndex("mimetype"))
                                .equals(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)) {
                          String  maddress = dataCursor.getString(dataCursor
                                    .getColumnIndex("data1"));// get company
                            // name
                            contactOtherDetails += "Company Name : "
                                    + maddress + "\n";
                            title = dataCursor.getString(dataCursor
                                    .getColumnIndex("data4"));// get Company
                            mAddFullAddress.setText(maddress);
                            // title
                            contactOtherDetails += "Title : " + title + "\n";

                        }








                    } while (dataCursor.moveToNext()); // Now move to next
                    // cursor


                    // items to
                    // array list
                }

            } while (contactsCursor.moveToNext());
        }

//        if (requestCode == RQS_PICK_CONTACT) {
//            if (resultCode == RESULT_OK) {
//                Uri contactData = data.getData();
//                Cursor cursor = getActivity().managedQuery(contactData, null, null , null, null);
//                cursor.moveToFirst();
//
//                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                String phoneticname = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHONETIC_NAME));
//                int type = ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM;
//                if(type==0){
//                  String  workEmail = cursor.getString(cursor
//                            .getColumnIndex("data1"));
//                    city.setText(workEmail);
//
//                }
//                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//
//                String email = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.CONTACT_ID));
//                String relation = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.SUFFIX));
//
//
//
//
//                contactName.setText("Full Name : "+name);
//                contactNumber.setText("Phone Number : "+number);
//                pName.setText("Phonetic Name : "+phoneticname);
////                state.setText(relation);
////                code.setText(email);
//
//                cursor.close();






            }

}

