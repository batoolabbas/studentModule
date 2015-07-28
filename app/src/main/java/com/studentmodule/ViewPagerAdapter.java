package com.studentmodule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import SH02.*;
import SH03.*;
import SH01.*;

/**
 * Created by aliabbasjaffri on 08/07/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    private int[] tabLayoutImages = {
            R.drawable.mypage,
            R.drawable.convo,
            R.drawable.enrollment,
            R.drawable.nowon
    };

    private String tabTitles[] = new String[] { "마이페이지", "쌤께 말걸기", "수강신청" , "Now On" };


    public ViewPagerAdapter(FragmentManager fm , Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public int getCount() {
        // Returns the number of tabs
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        // Returns a new instance of the fragment
        switch (position) {
            case 0:
                return MyPageFragment.newInstance(1);
            case 1:
                return SH03.newInstance();
            case 2:
                return SH02_2.newInstance();
            case 3:
                return SH01.newInstance();
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        // Generate title based on item position
        Drawable image = context.getResources().getDrawable(tabLayoutImages[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth()/3, image.getIntrinsicHeight()/3);

        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString( " \n" + tabTitles[position] );
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}