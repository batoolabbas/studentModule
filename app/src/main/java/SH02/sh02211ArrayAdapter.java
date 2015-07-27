package SH02;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 27/07/15.
 */
public class sh02211ArrayAdapter extends ArrayAdapter<sh02_2_1_1Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh02_2_1_1Data> items = new ArrayList<>();

    public sh02211ArrayAdapter(Context context, int resource, ArrayList<sh02_2_1_1Data> data)
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

        final sh02_2_1_1Data item = items.get(position);

        ImageView profileImage = (ImageView) convertView.findViewById(R.id.sh02_2_1_1TutorImageView);
        profileImage.setImageResource(item.getTutorImage());

        TextView tutorName = (TextView) convertView.findViewById(R.id.sh02_2_1_1TutorNameTextView);
        tutorName.setText(item.getTutorName());

        Button selectTutorButton = (Button) convertView.findViewById(R.id.sh02_2_1_1SelectTutorButton);
        selectTutorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText( context , "Tutor" + position + "Selected", Toast.LENGTH_SHORT ).show();
            }
        });

        return convertView;
    }
}