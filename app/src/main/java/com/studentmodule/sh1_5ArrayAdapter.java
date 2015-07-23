package com.studentmodule;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 23/07/15.
 */
public class sh1_5ArrayAdapter extends ArrayAdapter<sh1_5Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh1_5Data> items;// = new ArrayList<>();

    public sh1_5ArrayAdapter(Context context, int resource , ArrayList <sh1_5Data> items)
    {
        super(context, resource);

        this.context = context;
        XML_ID = resource;
        //this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;

        if (convertView == null)
        {
            LayoutInflater li = ((Activity) context).getLayoutInflater();
            row = li.inflate( R.layout.layout_sh1_5_custom_row , parent, false);
        }
        else
        {
            row = (View) convertView;
        }

        sh1_5Data item = items.get(position);

        Button button = (Button) row.findViewById(R.id.sh1_5Button);
        button.setText(item.getButtonInfo());
        if( item.getButtonType() )
            button.setBackgroundResource(R.drawable.regular);
        else
            button.setBackgroundResource(R.drawable.trial);

        TextView listInfo = (TextView) row.findViewById(R.id.sh1_5ListInfo);
        listInfo.setText(item.getListInfo());

        TextView listDetail = (TextView) row.findViewById(R.id.sh1_5ListDetail);
        listDetail.setText(item.getListDetail());

        Log.i("Hello", "In GetView");

        return row;
    }
}
