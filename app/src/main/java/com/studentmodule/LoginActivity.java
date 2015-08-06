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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!preferences.contains("SkypeID")) {


            setContentView(R.layout.activity_login);

            Toolbar toolbar = (Toolbar) findViewById(R.id.loginActivityInclude);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText("Login");
            setSupportActionBar(toolbar);


            final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberField);
            //PHONE NUMBER DOES NOT MAKE SENSE -.- SO I'M ASSUMING THIS WILL BE SKYPE ID !
            //String loginPhoneNumber = phoneNumber.getText().toString();
            //Log.d("phoneNumber" , loginPhoneNumber);

            Button next = (Button) findViewById(R.id.nextButton);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String loginPhoneNumber = phoneNumber.getText().toString();
                    Log.d("phoneNumber", loginPhoneNumber);

                    //phone number se somehow skypeid deduce kerke shared preferences mein store kerwana hai!
                    //NOTE: Find out which table to use for this


                    Intent i = new Intent(LoginActivity.this, StudentFirstActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
