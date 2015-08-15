package com.studentmodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Intro.IntroActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

import SHA.shAData;
import utils.AppConfig;


public class LoginActivity extends ActionBarActivity {

    int textlength;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!preferences.contains("SkypeID")) {



            Toolbar toolbar = (Toolbar) findViewById(R.id.loginActivityInclude);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Login");
            setSupportActionBar(toolbar);

            textlength = 0;

            final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberField);
            phoneNumber.addTextChangedListener(new TextWatcher()
            {

                public void afterTextChanged(Editable s)
                {

                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    String text = phoneNumber.getText().toString();
                    textlength = phoneNumber.getText().length();

                    if(text.endsWith(" "))
                        return;

                    if(textlength == 3 || textlength == 8 )
                    {
                        phoneNumber.setText(new StringBuilder(text).insert(text.length(), "-").toString());
                        phoneNumber.setSelection(phoneNumber.getText().length());
                    }

                }
            }
        );



        Button next = (Button) findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String loginPhoneNumber = phoneNumber.getText().toString();
                Log.d("phoneNumber" , loginPhoneNumber);

                final String tag_string_req= "getLogin";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        AppConfig.TUTOR_API_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {


                        try {
                            JSONObject jObj = new JSONObject(s);
//                    boolean error =jObj.getBoolean("error");
//
                            final String SkypeID = jObj.getString("userid");
                            final int Points = jObj.getInt("point");

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("SkypeID", SkypeID);
                            editor.putInt("Points",Points);
                            editor.commit();

//                    if(!error)
//                    {
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //exception handling for failing to get teacher data
                    }

                }){
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("tag",tag_string_req);
                        params.put("phone",loginPhoneNumber);
                        return params;
                    }
                };



                Intent i = new Intent( LoginActivity.this , IntroActivity.class );
                startActivity(i);

            }
        });

        }
        else
        {
            Intent i = new Intent(LoginActivity.this, StudentFirstActivity.class);
            startActivity(i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
