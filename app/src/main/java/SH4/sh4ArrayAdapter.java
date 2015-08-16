package SH4;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 03/08/15.
 */
public class sh4ArrayAdapter extends ArrayAdapter<sh4Data>
{
    Context context;
    int XML_ID;
    ArrayList<sh4Data> items = new ArrayList<>();

    public sh4ArrayAdapter(Context context, int resource, ArrayList<sh4Data> data)
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

        final sh4Data item = items.get(position);

        ImageView badgeImage = (ImageView) convertView.findViewById(R.id.sh4RowTutorBadgeImageView);
        badgeImage.setImageResource(item.getBadgeImage());

        ImageView profileImage = (ImageView) convertView.findViewById(R.id.sh4RowTutorImageView);
        profileImage.setImageBitmap(Bitmap.createScaledBitmap(item.getProfilePicture(), profileImage.getWidth(), profileImage.getHeight(), true));

        TextView tutorName = (TextView) convertView.findViewById(R.id.sh4RowTutorNameTextView);
        tutorName.setText(item.getTutorName());

        ImageView tutorCountryImage = (ImageView) convertView.findViewById(R.id.sh4RowTutorCountryImageView);
        tutorCountryImage.setImageResource(item.getTutorCountry());

        return convertView;
    }
}