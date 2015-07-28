package SH01;

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

import SH2.sh2_3Data;

/**
 * Created by aliabbasjaffri on 29/07/15.
 */
public class sh01ArrayAdapter extends ArrayAdapter<sh01Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh01Data> items = new ArrayList<>();

    public sh01ArrayAdapter(Context context, int resource, ArrayList<sh01Data> data)
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

        int availableIcon;
        String availableText;
        int nowOnButtonImage;

        final sh01Data item = items.get(position);

        ImageView tutorImageView = (ImageView) convertView.findViewById(R.id.sh01TutorImageView);
        tutorImageView.setImageResource(item.getTutorImage());

        TextView tutorName = (TextView) convertView.findViewById(R.id.sh01TutorNameTextView);
        tutorName.setText(item.getTutorName());

        if( item.getAvailability() )
        {
            availableIcon = R.drawable.now_on_texticon;
            availableText = "Online";
            nowOnButtonImage = R.drawable.now_on;
        }
        else
        {
            availableIcon = R.drawable.now_off_texticon;
            availableText = "Offline";
            nowOnButtonImage = R.drawable.now_off;
        }

        ImageView availabilityIcon = (ImageView) convertView.findViewById(R.id.sh01StatusImageView);
        availabilityIcon.setImageResource(availableIcon);

        TextView availabilityText = (TextView) convertView.findViewById(R.id.sh01StatusTextView);
        availabilityText.setText(availableText);

        Button nowOnButton = (Button) convertView.findViewById(R.id.sh01NowOnButton);
        nowOnButton.setBackgroundResource(nowOnButtonImage);
        nowOnButton.setEnabled(item.getAvailability());
        nowOnButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context , "Touched " + item.getTutorName() , Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}