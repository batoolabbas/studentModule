package SH1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.studentmodule.R;

import java.util.ArrayList;

import SH1.sh1_5_1Data;

/**
 * Created by aliabbasjaffri on 25/07/15.
 */
public class sh151ArrayAdapter extends ArrayAdapter<sh1_5_1Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh1_5_1Data> items = new ArrayList<>();

    public sh151ArrayAdapter(Context context, int resource, ArrayList<sh1_5_1Data> data)
    {
        super(context, resource , data);

        this.context = context;
        XML_ID = resource;
        this.items = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(XML_ID, null);
        }

        final sh1_5_1Data item = items.get(position);

        TextView subText = (TextView) convertView.findViewById(R.id.sh1_5_1TextViewSub);
        subText.setText(item.getSubscript());

        TextView mainText = (TextView) convertView.findViewById(R.id.sh1_5_1TextViewMain);
        mainText.setText(item.getMain());

        return convertView;
    }
}