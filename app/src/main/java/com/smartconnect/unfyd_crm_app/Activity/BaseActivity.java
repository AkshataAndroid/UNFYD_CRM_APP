package com.smartconnect.unfyd_crm_app.Activity;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

    // public Analyticsclass nMyApplication;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //  nMyApplication = (Analyticsclass) getApplication();
        //  nMyApplication.onActivityCreated(this, savedInstanceState);
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //   nMyApplication.onActivityResumed(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        // nMyApplication.onActivityPaused(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //  nMyApplication.onActivityDestroyed(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // nMyApplication.onActivityStarted(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //   nMyApplication.onActivityStopped(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //  nMyApplication.onActivitySaveInstanceState(this, outState);
    }
}