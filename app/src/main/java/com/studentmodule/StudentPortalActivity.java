package com.studentmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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
    CustomViewPager mViewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;

    Toolbar toolbar;
    TextView mTitle;
    protected  StudentPortalActivity instance;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal);

        instance = this;

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager() , this.getApplicationContext() , instance );
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setPagingEnabled(false);
        mViewPager.setOffscreenPageLimit(viewPagerAdapter.getCount() - 1);
        mViewPager.setBackgroundColor(Color.parseColor("#3f355b"));

        toolbar = (Toolbar) findViewById(R.id.baseActivityToolbarInclude);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setToolbar(viewPagerAdapter.getPageName(0), false);

        tabLayout = (TabLayout) findViewById(R.id.baseActivityTabLayoutInclude);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setBackgroundColor(Color.parseColor("#f3f5f9"));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0)
            {
                clearBackStack();
                String title = viewPagerAdapter.getPageName(arg0);
                setToolbar(title, false);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Error" + Thread.currentThread().getStackTrace()[2], paramThrowable.getLocalizedMessage());
                System.gc();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_student_portal, menu);
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

    public void setToolbar(String title, boolean backButton)
    {
        if (toolbar != null && mTitle != null)
        {
            mTitle.setText(title);
            setSupportActionBar(toolbar);

            Log.d("BackButton", " " + backButton);

            if (backButton)
            {
                toolbar.setNavigationIcon(R.drawable.back_arrow);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            else
            {
                toolbar.setNavigationIcon(null);
                getSupportActionBar().setHomeButtonEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }

            toolbar.setNavigationOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onBackPressed()
    {
        viewPagerAdapter.notifyDataSetChanged();
        super.onBackPressed();
    }


    @Override
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }



    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }




    public void clearBackStack()
    {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    @Override
    protected void onDestroy()
    {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}
