package com.studentmodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import helper.SQLiteHandler;
import utils.AppConfig;
import utils.AppController;


public class SignUp extends ActionBarActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText phoneNumberField;
    private EditText passwordField;
    private EditText reEnterPasswordField;
    private EditText skypeIDField;
    private EditText birthdayField;

    private Spinner levelSelect;

    private Button skypeDownload;
    private Button confirmSignUp;

    String englishLevel;
    String [] levels;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        levels = getResources().getStringArray(R.array.spinnerItems);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signupActivityInclude);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Sign up");
        setSupportActionBar(toolbar);

        nameField = (EditText) findViewById(R.id.nameField);
        final String name = nameField.getText().toString();

        emailField = (EditText) findViewById(R.id.emailField);
        final String email = emailField.getText().toString();

        phoneNumberField = (EditText) findViewById(R.id.phoneNumberField);
        final String phoneNumber = phoneNumberField.getText().toString();

        passwordField = (EditText) findViewById(R.id.passwordField);
        final String password = passwordField.getText().toString();

        reEnterPasswordField = (EditText) findViewById(R.id.reEnterPasswordField);
        final String reEnterPassword = reEnterPasswordField.getText().toString();

        skypeIDField = (EditText) findViewById(R.id.skypeIDField);
        final String skypeID = skypeIDField.getText().toString();

        birthdayField = (EditText) findViewById(R.id.birthdayField);
        final String birthday = birthdayField.getText().toString();

        levelSelect = (Spinner) findViewById(R.id.englishLevelSpinner);

        levelSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                englishLevel = levels[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                englishLevel = levelSelect.getSelectedItem().toString();
            }
        });

        skypeDownload = (Button) findViewById(R.id.downloadSkypeButton);
        skypeDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        confirmSignUp = (Button) findViewById(R.id.completionButton);
        confirmSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.contentEquals(reEnterPassword)) {
                    //batool's code starts here
                    UserDetails ud = new UserDetails(name, skypeID, password, phoneNumber, email, englishLevel);
                    registerUser(ud);
                    //batool's code ends here


                    Intent i = new Intent(SignUp.this, StudentFirstActivity.class);

                    editor.putString("EnglishLevel", englishLevel);
                    editor.putString("SkypeID", skypeID);
                    Log.i("English Level", englishLevel);
                    editor.commit();

                    startActivity(i);
                }
                else {
                    //passwords don't match do whatever needs to be done
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class UserDetails
    {
        String Name, skype, pswd, email, phoneNum, englishLvl;

        public String getname() {
            return Name;
        }

        public void setname(String fname) {
            this.Name = fname;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String sx) {
            this.phoneNum = sx;
        }

        public String getEmail(){ return email;}

        public void setEmail(String email){ this.email=email;}

        public String getPswd(){ return pswd;}

        public void setPswd(String pwd){this.pswd = pwd;}

        public String getEnglishLvl(){ return englishLvl; }

        public void setEnglishLvl(String elvl){ this.englishLvl=elvl; }

        UserDetails(String n, String sk,String p, String pn,String e, String elvl)
        {
            Name = n;
            skype = sk;
            pswd = p;
            phoneNum = pn;
            email=e;
            englishLvl=elvl;
        }
    }

    private void registerUser(final UserDetails ud) {
        // Tag used to cancel the request
        String tag_string_req = "signup";

        //      pDialog.setMessage("Registering ...");
        //     showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.TUTOR_API_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "Register Response: " + response.toString());

                        //                       hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {

//                        Toast.makeText(getApplicationContext(),
//                                ud.toString(), Toast.LENGTH_LONG).show();

                                SQLiteHandler mydb =  SQLiteHandler.getInstance(getApplicationContext());
                                mydb.addUser(ud);

                                UserData.skypeid = ud.skype;

                                UserData.name = ud.Name;
//                        Toast.makeText(getApplicationContext(),
//                                "user stored", Toast.LENGTH_LONG).show();

                                //       Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                                //       startActivity(intent);

                                //finish();
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //               hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("tag", "signup");
                params.put("name", ud.getname());
                params.put("email", ud.getEmail());
                params.put("pwd", ud.getPswd());
                params.put("skypeid", ud.getSkype());
                params.put("email", ud.getEmail());

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
