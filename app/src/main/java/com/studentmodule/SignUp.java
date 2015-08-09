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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


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

    int textlength;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        levels = getResources().getStringArray(R.array.spinnerItems);

        textlength = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.signupActivityInclude);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Sign up");
        setSupportActionBar(toolbar);

        nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        emailField = (EditText) findViewById(R.id.emailField);
        String email = emailField.getText().toString();

        phoneNumberField = (EditText) findViewById(R.id.phoneNumberField);
        phoneNumberField.addTextChangedListener(new TextWatcher()
            {
                public void afterTextChanged(Editable s)
                {

                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }

                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    String text = phoneNumberField.getText().toString();
                    textlength = phoneNumberField.getText().length();

                    if(text.endsWith(" "))
                        return;

                    if(textlength == 3 || textlength == 8 )
                    {
                        phoneNumberField.setText(new StringBuilder(text).insert(text.length(), "-").toString());
                        phoneNumberField.setSelection(phoneNumberField.getText().length());
                    }

                }}
        );


        String phoneNumber = phoneNumberField.getText().toString();

        passwordField = (EditText) findViewById(R.id.passwordField);
        String password = passwordField.getText().toString();

        reEnterPasswordField = (EditText) findViewById(R.id.reEnterPasswordField);
        String reEnterPassword = reEnterPasswordField.getText().toString();

        skypeIDField = (EditText) findViewById(R.id.skypeIDField);
        final String skypeID = skypeIDField.getText().toString();

        birthdayField = (EditText) findViewById(R.id.birthdayField);
        String birthday = birthdayField.getText().toString();

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
                Intent i = new Intent(SignUp.this, StudentFirstActivity.class);

                editor.putString("EnglishLevel", englishLevel);
                editor.putString("SkypeID", skypeID);
                Log.i("English Level", englishLevel);
                editor.commit();

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }
}
