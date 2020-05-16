package com.smartconnect.unfyd_crm_app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartconnect.unfyd_crm_app.Fragments.ContactsFragment;
import com.smartconnect.unfyd_crm_app.Fragments.OpportunitiesFragment;
import com.smartconnect.unfyd_crm_app.Fragments.TasksFragment;
import com.smartconnect.unfyd_crm_app.Model.ContactList;
import com.smartconnect.unfyd_crm_app.Model.OpportunityList;
import com.smartconnect.unfyd_crm_app.Model.TaskCurrentModel;
import com.smartconnect.unfyd_crm_app.Model.TaskPendingmodel;
import com.smartconnect.unfyd_crm_app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ArrayList<ContactList> arrayOfUsers = new ArrayList<ContactList>();
    ArrayList<OpportunityList> arrayOfOpportunity = new ArrayList<OpportunityList>();
    ArrayList<TaskCurrentModel> arrayOfCurrent = new ArrayList<TaskCurrentModel>();

    ArrayList<TaskPendingmodel> arrayOfPending = new ArrayList<TaskPendingmodel>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bottomNavigationView= findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.setSelectedItemId(R.id.navigation_opportunities);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        hideBottomBar(false);
        loadFragment(new OpportunitiesFragment());
        //database

    }
    public void hideBottomBar(boolean isHidden){
        bottomNavigationView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }
    public ArrayList<ContactList> contact(ContactList contactLists){
       // bottomNavigationView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
        if(contactLists!= null){
            arrayOfUsers.add(contactLists);
        }

        return arrayOfUsers;

    }
    public ArrayList<OpportunityList> opportunity(OpportunityList opportunityList){
        // bottomNavigationView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
        if(opportunityList!= null){
            arrayOfOpportunity.add(opportunityList);
        }

        return arrayOfOpportunity;

    }
    public ArrayList<TaskCurrentModel> taskCurrentModels(TaskCurrentModel currentlist){
        // bottomNavigationView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
        if(currentlist!= null){
            arrayOfCurrent.add(currentlist);
        }

        return arrayOfCurrent;

    }
    public ArrayList<TaskPendingmodel> taskPendingmodels(TaskPendingmodel pendinglist){
        // bottomNavigationView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
        if(pendinglist!= null){
            arrayOfPending.add(pendinglist);
        }

        return arrayOfPending;

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            Fragment fragment = null;
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
          //  Fragment currentFragment= this.findFragmentById(R.id.frame_container);
            switch (item.getItemId()) {
                case R.id.navigation_tasks:
                    if (!(currentFragment instanceof TasksFragment)) {
                        fragment = new TasksFragment();
                        loadFragment(fragment);

                    }
                    return true;
                case R.id.navigation_contacts:
                    if (!(currentFragment instanceof ContactsFragment)) {
                        fragment = new ContactsFragment();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.navigation_opportunities:
                    fragment = new OpportunitiesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_settings:
//                    fragment = new DetailsActivity();
//                    loadFragment(fragment);
                    Intent intent = new Intent(FirstActivity.this, DetailsActivity.class);
                    startActivity(intent);
                    return true;

            }

            return false;
        }
    };
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //     getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //   getSupportActionBar().setDisplayShowHomeEnabled(false);
            //changeToolBarText("Survey");
            //finish();
            //finish();
        }
    }
    private boolean loadFragment(Fragment fragment) {
        // load fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}