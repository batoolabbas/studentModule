package com.studentmodule;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import SHA.*;

/**
 * Created by aliabbasjaffri on 01/08/15.
 */
public class studentFirstActivityPagerAdapter extends FragmentPagerAdapter
{
    private Context context;
    private ArrayList<SHA> tutorsList;

    public studentFirstActivityPagerAdapter(FragmentManager fm, Context context , ArrayList<SHA> TutorsList )
    {
        super(fm);
        this.context = context;
        tutorsList = TutorsList;
    }

    @Override
    public Fragment getItem(int position)
    {
        return tutorsList.get(position);
    }

    @Override
    public int getCount()
    {
        return tutorsList.size();
    }
}
