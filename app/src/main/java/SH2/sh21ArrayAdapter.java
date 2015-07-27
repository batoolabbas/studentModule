package SH2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.studentmodule.R;
import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 25/07/15.
 */
public class sh21ArrayAdapter extends ArrayAdapter<sh2_1Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh2_1Data> items = new ArrayList<>();

    public sh21ArrayAdapter(Context context, int resource, ArrayList<sh2_1Data> data)
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

        final sh2_1Data item = items.get(position);

        TextView columnOne = (TextView) convertView.findViewById(R.id.sh2_1ColumnOneTextView);
        columnOne.setText(item.getColumnOne());

        TextView columnTwo = (TextView) convertView.findViewById(R.id.sh2_1ColumnTwoTextView);
        columnTwo.setText(item.getColumnTwo());

        TextView columnThree = (TextView) convertView.findViewById(R.id.sh2_1ColumnThreeTextView);
        columnThree.setText(item.getColumnThree());

        return convertView;
    }
}