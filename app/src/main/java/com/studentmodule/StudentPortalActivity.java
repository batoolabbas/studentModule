package com.studentmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import SH1.*;


public class StudentPortalActivity extends AppCompatActivity
{
    ViewPager mViewPager;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal);
        setToolbar("Student Mode" , false);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager() , StudentPortalActivity.this));
        mViewPager.setBackgroundColor(Color.parseColor("#3f355b"));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.baseActivityTabLayoutInclude);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setBackgroundColor(Color.parseColor("#f3f5f9"));

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                if (f != null) {
                    f.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    updateFragmentName(f);
                }
                else if ( f.getClass().getName().equals("") || f.getClass().getName().equals("MyPageFragment"))
                    setToolbar("Student Module", false);

            }
        });

        getSupportFragmentManager().removeOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged()
            {
                getSupportFragmentManager().popBackStack();

                Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                if (f != null)
                {
                    f.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    updateFragmentName(f);
                }
                else
                    setToolbar("Student Module", false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_portal, menu);
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

    public void setToolbar(String title , boolean backButton)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.baseActivityToolbarInclude);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        mTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(backButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backButton);

        if(backButton)
        {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    private void updateFragmentName (Fragment fragment){
        String fragClassName = fragment.getClass().getName();

        Log.i("Frag Name" , fragClassName);

        if (fragClassName.equals(MyPageFragment.class.getName()))
        {
            setToolbar("Student  Module", false);

            //set selected item position, etc
        }
        else if (fragClassName.equals(SH1.class.getName()))
        {
            setToolbar("SH1", true);
            //getSupportFragmentManager().popBackStack();
            //set selected item position, etc
        }
        else if (fragClassName.equals(SH1_5.class.getName())){
            setToolbar("SH1_5", true);
            //set selected item position, etc
        }
        else if (fragClassName.equals(SH1_5_1.class.getName())){
            setToolbar("SH1_5_1", true);
            //set selected item position, etc
        }
    }

    @Override
    public void onBackPressed()
    {
        if( getSupportFragmentManager().getBackStackEntryCount() > 0 )
        {
            getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryCount() , 1);
            super.onBackPressed();
        }
    }
}
