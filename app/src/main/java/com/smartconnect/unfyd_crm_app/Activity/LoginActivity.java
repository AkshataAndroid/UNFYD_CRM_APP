package com.smartconnect.unfyd_crm_app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.smartconnect.unfyd_crm_app.Constants.Apiconstants;
import com.smartconnect.unfyd_crm_app.Handler.RequestHandler;
import com.smartconnect.unfyd_crm_app.Model.User;
import com.smartconnect.unfyd_crm_app.R;
import com.smartconnect.unfyd_crm_app.SharedPreferance.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    Button loginButton;
EditText mUsername,mPassword;
    String message;
   ProgressDialog pd;
   ProgressBar progressBar;
    String response;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        mUsername = (EditText) findViewById(R.id.edituser);
        mPassword = (EditText) findViewById(R.id.editpassword);
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   userLogin();
                startActivity(new Intent(getApplicationContext(), FirstActivity.class));



            }
        });

    }
    private void userLogin() {
        //first getting the values
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashInBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
      final String pass=  sb.toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            mUsername.setError("Please enter your username");
            mUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Please enter your password");
            mPassword.requestFocus();
            return;
        }

        //if everything is fine

      class UserLogin extends AsyncTask<Void, Void, String> {

          ProgressBar progressBar;

          @Override
          protected void onPreExecute() {
              super.onPreExecute();
              progressBar = (ProgressBar) findViewById(R.id.progressBar);
              progressBar.setVisibility(View.VISIBLE);
          }

          @Override
          protected void onPostExecute(String s) {
              super.onPostExecute(s);
              progressBar.setVisibility(View.GONE);

              try {
                  //converting response to json object

                  JSONObject obj = new JSONObject(response);

                  //if no error in response
                  if (obj.optString("Result").equals("Success")) {
                      Toast.makeText(getApplicationContext(), obj.getString("Result"), Toast.LENGTH_SHORT).show();

                      // JSONObject object = new JSONObject(s);
                      JSONArray userArray = obj.getJSONArray("data");
                      // implement for loop for getting users list data
                      for (int i = 0; i < userArray.length(); i++) {
                          JSONObject userJson = userArray.getJSONObject(i);
                          User user = new User(
                                  userJson.getString("RoleName"),
                                  userJson.getString("Email"),
                                  userJson.getString("RoleID"),
                                  userJson.getString("Capacity"),
                                  userJson.getString("Mobile"),
                                  userJson.getString("UserID"),
                                  userJson.getString("UserName")
                          );
                          // create a object for getting contact data from JSONObject
                          SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                      }

                      // if(mUsername==true){
                      startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                      finish();
//                             }else{
//
//                             }

                  } else {
                      Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                  }
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      

          @Override
          protected String doInBackground(Void... voids) {
              //creating request handler object
              RequestHandler requestHandler = new RequestHandler();

              //creating request parameters
              HashMap<String, String> params = new HashMap<>();
              params.put("emailID", username);
              params.put("password", pass);
              response=requestHandler.sendPostRequest(Apiconstants.URL_LOGIN);
              //returing the response
              return response;
          }

        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
    @Override
    public void onPause() {
        super.onPause();
       savePreferences();

    }
    @Override
    public void onResume() {
        super.onResume();
       loadPreferences();
    }
    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UnameValue = mUsername.getText().toString();
      //  PasswordValue = mPassword.getText().toString();

        editor.putString(PREF_UNAME, UnameValue);
        editor.putString(PREF_PASSWORD, PasswordValue);
        editor.commit();
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
      //  PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        mUsername.setText(UnameValue);
       // mPassword.setText(PasswordValue);

    }
}









