package com.smartconnect.unfyd_crm_app.Fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.database.CRMdb;

import net.sqlcipher.database.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class AddContactsFragment extends Fragment {
   //fragments
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    EditText mWhatsappno,mPhoneNumber,mFullName,mEmailpersonal,mEmailoffice,mSalutation,mFirstname,mLastname,mAddFullAddress,mFacebook,mTwitter,mLinkedin;
    TextView mName ,mPhone,viewmore,mEmail,mSocial,infotext,mLanguage,mGender,mDesignation,mIndustry,mGtm,mDuedate,mOrganisation
            ,mSelectDate,mAddress,mCountry,mState,mCity,mZipcode,mFullAddress;
    RelativeLayout namepanal,phonepanal,onlynamePanal,emailpanal,socialpanal;
    ImageView uparrow,plusimage,whatsminus,numberminus,emailoneminus,emailtwominus,facebookminus,twitterminus,linkedinminus,downarrow,plusEmailimage,moreimage,socialimage,plusSocial,infoimage,infodown,upcontact,downcontact,calenderimage,infoaddress,upaddress,downaddress,importicon;
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
    View view;

    public AddContactsFragment() {
        // Required empty public constructor
    }


    public static AddContactsFragment newInstance(String param1, String param2) {
        AddContactsFragment fragment = new AddContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_add_contacts, container, false);
        SQLiteDatabase.loadLibs(getContext());
        myCalendar = Calendar.getInstance();
        init();
        visiblity();
        spinners();
        onclicks();
       CardView cardView = view.findViewById(R.id.card_view);
        cardView.setBackgroundResource(R.drawable.cardview);
        FirstActivity activity = (FirstActivity) getActivity();
        if (activity != null)
            activity.hideBottomBar(true);
      //  cotactsdb = new DatabaseHelper(getActivity());



        return view;
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

                //database
                ContentValues contentValues = new ContentValues();
                // get  & set with contentvalues
                contentValues.put(CRMdb.FULLNAME,fullname );
                contentValues.put(CRMdb.MOBILENUMBER, number);
                CRMdb lb = new CRMdb(getContext());
                long row = lb.addcontactItem(fullname,number);
                if (row > 0) {
                    Toast.makeText(getContext(), "Your data Inserted Successfully....",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Something Wrong...", Toast.LENGTH_SHORT).show();
                }

                ContactsFragment contactsFragment = new ContactsFragment();
                contactsFragment.setArguments(bundle);
//                bottomNavigationView.setVisibility(View.VISIBLE);
                assert getFragmentManager() != null;
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,contactsFragment );
                  transaction.addToBackStack(null);
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
//                Intent i=new Intent(AddContactsActivity.this, FirstActivity.class);
//                startActivity(i);
//        Fragment newCase=new ContactsFragment();
//        FragmentTransaction transaction=getFragmentManager().beginTransaction();
//        transaction.replace(R.id.content,newCase); // give your fragment container id in first parameter
//        transaction.commit();
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }


            }
        });
        calenderimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        importicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.READ_CONTACTS)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
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
                mEmailoffice.setVisibility(View.VISIBLE);
                mEmailpersonal.setVisibility(View.VISIBLE);
                emailSpinner.setVisibility(View.VISIBLE);
                emailtwoSpinner.setVisibility(View.VISIBLE);
                emailtwominus.setVisibility(View.VISIBLE);
                emailoneminus.setVisibility(View.VISIBLE);
              //  emailview.setVisibility(View.VISIBLE);


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
                mFacebook.setVisibility(View.VISIBLE);
                mTwitter.setVisibility(View.VISIBLE);
                mLinkedin.setVisibility(View.VISIBLE);
                facebookminus.setVisibility(View.VISIBLE);
                twitterminus.setVisibility(View.VISIBLE);
                linkedinminus.setVisibility(View.VISIBLE);
              //  socialview.setVisibility(View.VISIBLE);
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
                mPhoneNumber.setVisibility(View.VISIBLE);
                mWhatsappno.setVisibility(View.VISIBLE);
                phoneSpinner.setVisibility(View.VISIBLE);
                numberminus.setVisibility(View.VISIBLE);
                whatsAppSpinner.setVisibility(View.VISIBLE);
                whatsminus.setVisibility(View.VISIBLE);
              //  phoneview.setVisibility(View.VISIBLE);
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
        numberminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneNumber.setVisibility(View.GONE);
                phoneSpinner.setVisibility(View.GONE);
                numberminus.setVisibility(View.GONE);

            }
        });
        whatsminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhatsappno.setVisibility(View.GONE);
                whatsAppSpinner.setVisibility(View.GONE);
                whatsminus.setVisibility(View.GONE);

            }
        });
        emailoneminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailpersonal.setVisibility(View.GONE);
                emailSpinner.setVisibility(View.GONE);
                emailoneminus.setVisibility(View.GONE);

            }
        });
        emailtwominus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailoffice.setVisibility(View.GONE);
                emailtwoSpinner.setVisibility(View.GONE);
                emailtwominus.setVisibility(View.GONE);

            }
        });
        facebookminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFacebook.setVisibility(View.GONE);
                facebookminus.setVisibility(View.GONE);

            }
        });
        twitterminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTwitter.setVisibility(View.GONE);
                twitterminus.setVisibility(View.GONE);

            }
        });

        linkedinminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinkedin.setVisibility(View.GONE);
                linkedinminus.setVisibility(View.GONE);

            }
        });

    }
//    public void AddData(String firstName,String number ){
//        boolean insertData = cotactsdb.addData(firstName,number);
//
//        if(insertData){
//            Toast.makeText(getActivity(),"Successfully Entered Data!",Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(getActivity(),"Something went wrong :(.",Toast.LENGTH_LONG).show();
//        }
//
//    }

    private void updateLabel() {
        String myFormat = "dd MMM YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mSelectDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void spinners() {
        //spinnerforphone
        phoneSpinner = (Spinner) view.findViewById(R.id.phonespinner);
        List<String> list = new ArrayList<String>();
        list.add("Mobile");
        list.add("Landline");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.phone_spinner, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        phoneSpinner.setAdapter(dataAdapter);

        //whatsappspinner
        whatsAppSpinner = (Spinner) view.findViewById(R.id.whatsapppinner);
        List<String> wlist = new ArrayList<String>();
        wlist.add("WANo1");
        wlist.add("WANo2");

        ArrayAdapter<String> whatsaapAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.phone_spinner, wlist);
        whatsaapAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        whatsAppSpinner.setAdapter(whatsaapAdapter);

        //emailSpinner
        List<String> emaillist = new ArrayList<String>();
        emaillist.add("Work");
        emaillist.add("Personal");

        ArrayAdapter<String> emailAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.phone_spinner, emaillist);
        emailAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        emailSpinner.setAdapter(emailAdapter);

        List<String> emaillisttwo = new ArrayList<String>();
        emaillisttwo.add("Work");
        emaillisttwo.add("Personal");

        ArrayAdapter<String> emailtwoAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.phone_spinner, emaillisttwo);
        emailtwoAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        emailtwoSpinner.setAdapter(emailtwoAdapter);

        //languagespinner
        List<String> languagespinner = new ArrayList<String>();
        languagespinner.add("Select");
        languagespinner.add("English");
        languagespinner.add("Hindi");
        languagespinner.add("Marathi");

        ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, languagespinner);
        languageAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        langspinner.setAdapter(languageAdapter);
//gender
        List<String> genderlist = new ArrayList<String>();
        genderlist.add("Select");
        genderlist.add("Male");
        genderlist.add("Female");

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        ArrayAdapter<String> designationAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        ArrayAdapter<String> industrytypeAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, industrylist);
        industrytypeAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        industryspinner.setAdapter(industrytypeAdapter);

        //gtmpartner
        List<String> gtmpartnerlist = new ArrayList<String>();
        gtmpartnerlist.add("Select");
        gtmpartnerlist.add("Partner1");
        gtmpartnerlist.add("Partner2");


        ArrayAdapter<String> gtmpartnerAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, gtmpartnerlist);
        gtmpartnerAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        gtmspinner.setAdapter(gtmpartnerAdapter);

        //gtmpartner
        List<String> organisationlist = new ArrayList<String>();
        organisationlist.add("Select");
        organisationlist.add("SmartConnect");
        organisationlist.add("UNFYD");
        organisationlist.add("SmartOnApp");

        ArrayAdapter<String> organisationAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getActivity(),
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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this.getActivity(),
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


        ArrayAdapter<String> zipcodeAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.language_spinner, ziplistlist);
        zipcodeAdapter.setDropDownViewResource(R.layout.language_spinner_dropdown);
        zipcodespinner.setAdapter(zipcodeAdapter);

    }

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
        mFacebook=view.findViewById(R.id.facebook);
        mTwitter=view.findViewById(R.id.twitter);
        mLinkedin=view.findViewById(R.id.linkedin);
        downcontact=view.findViewById(R.id.downcontact);
        mName=view.findViewById(R.id.editName);
        submitButton=view.findViewById(R.id.submitbutton);
        mSalutation=view.findViewById(R.id.first);
        mFirstname=view.findViewById(R.id.middle);
        backbutton=view.findViewById(R.id.backbutton);
        mLastname=view.findViewById(R.id.last);
        mPhone=view.findViewById(R.id.editPhone);
        namepanal=view.findViewById(R.id.namepanal);
        viewmore=view.findViewById(R.id.viewmore);
        socialpanal=view.findViewById(R.id.socialpanal);
        mSocial=view.findViewById(R.id.socialtext);
        socialimage=view.findViewById(R.id.socialimg);
        socialview=view.findViewById(R.id.socialview);
        plusSocial=view.findViewById(R.id.plussocial);
        moreimage=view.findViewById(R.id.moreimage);
        uparrow=view.findViewById(R.id.up);
        downarrow=view.findViewById(R.id.down);
        onlynamePanal=view.findViewById(R.id.onlynamepanal);
        phonepanal=view.findViewById(R.id.phonepanal);
        //
        mWhatsappno=view.findViewById(R.id.whatsappnumber);
        mPhoneNumber=view.findViewById(R.id.phonenumber);
        mFullName=(EditText)view.findViewById(R.id.nametext);
        mEmailpersonal=view.findViewById(R.id.emailone);
        mEmailoffice=view.findViewById(R.id.emailtwo);
        whatsminus=view.findViewById(R.id.minustwo);
        numberminus=view.findViewById(R.id.minus);
        emailoneminus=view.findViewById(R.id.minusemail);
        emailtwominus=view.findViewById(R.id.minustwoemail);
        facebookminus=view.findViewById(R.id.minussocialone);
        twitterminus=view.findViewById(R.id.minussocialtwo);
        linkedinminus=view.findViewById(R.id.minussocialthree);
        nameview=view.findViewById(R.id.nameview);
        emailview=view.findViewById(R.id.emailview);
        //
        phoneview=view.findViewById(R.id.phoneview);
        plusimage=view.findViewById(R.id.plusimage);
        checkBox=view.findViewById(R.id.checkBoxcontact);
        mEmail=view.findViewById(R.id.editEmail);
        emailSpinner=view.findViewById(R.id.emailspinner);
        emailtwoSpinner=view.findViewById(R.id.emailtwospinner);
        plusEmailimage=view.findViewById(R.id.plusemail);
        emailpanal=view.findViewById(R.id.emailpanal);
        infodown=view.findViewById(R.id.downcontact);
        infoimage=view.findViewById(R.id.infoimage);
        infotext=view.findViewById(R.id.infotext);
        langspinner=view.findViewById(R.id.langspinner);
        mLanguage=view.findViewById(R.id.prelanguage);
        langspinnerview=view.findViewById(R.id.laguageview);
        upcontact=view.findViewById(R.id.upcontact);
        mGender=view.findViewById(R.id.gender);
        mDesignation=view.findViewById(R.id.designation);
        mIndustry=view.findViewById(R.id.intype);
        mGtm=view.findViewById(R.id.gtmpartner);
        mDuedate=view.findViewById(R.id.date);
        mOrganisation=view.findViewById(R.id.organizastio);
        mSelectDate=view.findViewById(R.id.selectdatetext);
        genderspinner=view.findViewById(R.id.genederspinner);
        Designationspinner=view.findViewById(R.id.designationspinner);
        industryspinner=view.findViewById(R.id.industryspinner);
        gtmspinner=view.findViewById(R.id.gtmspinner);
        organisationspinner=view.findViewById(R.id.organisationspinner);
        dateview=view.findViewById(R.id.dateview);
        calenderimage=view.findViewById(R.id.calenderimage);
        generview=view.findViewById(R.id.genderview);
        languageview=view.findViewById(R.id.laguageview);
        designationview=view.findViewById(R.id.designationview);
        industryview=view.findViewById(R.id.industryview);
        gtmview=view.findViewById(R.id.gtmview);
        orgview=view.findViewById(R.id.orgview);
        infoaddress=view.findViewById(R.id.infotwoimage);
        upaddress=view.findViewById(R.id.upaddress);
        downaddress=view.findViewById(R.id.downaddress);
        mAddress=view.findViewById(R.id.addresstext);
        mCountry=view.findViewById(R.id.country);
        mState=view.findViewById(R.id.state);
        mCity=view.findViewById(R.id.city);
        mZipcode=view.findViewById(R.id.zipcode);
        mFullAddress=view.findViewById(R.id.fulladdress);
        contryview=view.findViewById(R.id.countryview);
        stateview=view.findViewById(R.id.stateview);
        cityview=view.findViewById(R.id.cityview);
        zipcodeview=view.findViewById(R.id.zipcodeview);
        mAddFullAddress=view.findViewById(R.id.addfulladdress);
        countryspinner=view.findViewById(R.id.countryspinner);
        statespinner=view.findViewById(R.id.statespinner);
        cityspinner=view.findViewById(R.id.cityspinner);
        zipcodespinner=view.findViewById(R.id.zipcodespinner);
        importicon=view.findViewById(R.id.importicon);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = ContactsContract.Contacts.CONTENT_URI; // Contact URI
        Cursor contactsCursor = getActivity().getContentResolver().query(uri, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "); // Return



        if (contactsCursor.moveToFirst()) {
            do {
                long contctId = contactsCursor.getLong(contactsCursor
                        .getColumnIndex("_ID")); // Get contact ID
                Uri dataUri = ContactsContract.Data.CONTENT_URI; // URI to get
                // data of
                // contacts
                Cursor dataCursor = getActivity().getContentResolver().query(dataUri, null,
                        ContactsContract.Data.CONTACT_ID + " = " + contctId,
                        null, null);// Retrun data cusror represntative to
                // contact ID

                // Strings to get all details
                String displayName="" ;
                String nickName = "";
                String homePhone="" ;
                String mobilePhone="" ;
                String workPhone="" ;
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



