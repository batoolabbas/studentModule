package com.studentmodule;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import SH1.*;

public class TeacherProfileActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private static TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        toolbar = (Toolbar) findViewById(R.id.teacherProfileActivityToolbarInclude);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        changeToolbarTitle("Teacher Profile");


        Intent i = getIntent();
        String id = i.getStringExtra("teacherID");
        String name = i.getStringExtra("teacherName");
        Fragment fragment = SH1.newInstance(id,name);
        getSupportFragmentManager().beginTransaction().replace(R.id.teacherProfile, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_teacher_profile, menu);
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

    public static void changeToolbarTitle( String title )
    {
        mTitle.setText(title);
    }
}
