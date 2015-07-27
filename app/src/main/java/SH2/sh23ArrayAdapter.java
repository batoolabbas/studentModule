package SH2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.studentmodule.R;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 26/07/15.
 */
public class sh23ArrayAdapter extends ArrayAdapter<sh2_3Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh2_3Data> items = new ArrayList<>();

    public sh23ArrayAdapter(Context context, int resource, ArrayList<sh2_3Data> data)
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

        final sh2_3Data item = items.get(position);

        ImageView profileImage = (ImageView) convertView.findViewById(R.id.sh02_2_1_1TutorImageView);
        profileImage.setImageResource(item.getTutorImage());

        TextView tutorName = (TextView) convertView.findViewById(R.id.sh02_2_1_1TutorNameTextView);
        tutorName.setText(item.getTutorName());

        ImageView countryImage = (ImageView) convertView.findViewById(R.id.sh2_3TutorCountryImageView);
        countryImage.setImageResource(item.getTutorCountry());

        ImageButton deleteEntry = (ImageButton) convertView.findViewById(R.id.sh2_3DeleteTutorButton);
        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                items.remove(position);
                sh23ArrayAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}