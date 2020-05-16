package com.smartconnect.unfyd_crm_app.SharedPreferance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.smartconnect.unfyd_crm_app.Activity.LoginActivity;
import com.smartconnect.unfyd_crm_app.Model.User;

public class SharedPrefManager {


        private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
        private static final String ROLL_NAME = "rolename";
        private static final String KEY_EMAIL = "keyemail";
        private static final String ROLE_ID = "rid";
        private static final String CAPACITY = "cap";
    private static final String MOBILE = "mob";
    private static final String USERID = "uid";
    private static final String USERNAME = "uname";
    private final String DefaultUnameValue = "";


    private static SharedPrefManager mInstance;
        private static Context mCtx;

        private SharedPrefManager(Context context) {
            mCtx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            return mInstance;
        }

        //method to let the user login
        //this method will store the user data in shared preferences
        public void userLogin(User user) {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(ROLL_NAME, user.getRoleName());
            editor.putString(KEY_EMAIL, user.getEmail());
            editor.putString(ROLE_ID, user.getRoleID());
            editor.putString(CAPACITY, user.getCapacity());
            editor.putString(USERID, user.getUserID());
            editor.putString(MOBILE, user.getMobile());
            editor.putString(USERNAME, user.getUserName());
            editor.apply();
        }



    //this method will checker whether user is already logged in or not
        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_EMAIL, null) != null;
        }

        //this method will give the logged in user
        public User getUser() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new User(
                    sharedPreferences.getString(ROLL_NAME, null),
                    sharedPreferences.getString(KEY_EMAIL, null),
                    sharedPreferences.getString(ROLE_ID, null),
                    sharedPreferences.getString(CAPACITY, null),
                    sharedPreferences.getString(USERID, null),
                    sharedPreferences.getString(MOBILE, null),
                    sharedPreferences.getString(USERNAME, null)

                    );
        }

        //this method will logout the user
        public void logout() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
        }


    }

