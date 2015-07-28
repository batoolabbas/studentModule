package SH03;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 28/07/15.
 */
public class sh032ArrayAdapter extends BaseExpandableListAdapter
{
    ArrayList<sh03_2Data> items;
    Context context;
    private Boolean player = false;
    private MediaPlayer mPlayer;

    public sh032ArrayAdapter(ArrayList<sh03_2Data> itemss, Context context)
    {
        items = new ArrayList<>();

        this.items = itemss;
        this.context = context;
    }

    @Override
    public int getGroupCount()
    {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return items.get(groupPosition).data.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return items.get(groupPosition).getHeading();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return items.get(groupPosition).data.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_sh03_2_custom_parent_row, null);

        TextView heading = (TextView) convertView.findViewById(R.id.sh03_2ListHeadingTextView);
        heading.setText(items.get(groupPosition).getHeading());

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_sh03_2_custom_child_row, null);

        ImageView tutorProfileImage = (ImageView) convertView.findViewById(R.id.sh03_2TutorImageView);
        TextView tutorName = (TextView) convertView.findViewById(R.id.sh03_2TutorNameTextView);
        Button tutorRecordingPlayButton = (Button) convertView.findViewById(R.id.sh03_2PlayTutorVoiceButton);

        tutorProfileImage.setImageResource(items.get(groupPosition).data.get(childPosition).getTutorImage());
        tutorName.setText(items.get(groupPosition).data.get(childPosition).getTutorName());

        mPlayer = MediaPlayer.create( context , Uri.parse(items.get(groupPosition).data.get(childPosition).getVoiceLink()));

        //Must use Async Task
        tutorRecordingPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                player = !player;

                final String fileName = items.get(groupPosition).data.get(childPosition).getVoiceLink();

                if(!player)
                    mPlayer.reset();

                else {
                    mPlayer = MediaPlayer.create( context , Uri.parse(fileName));
                    mPlayer.start();
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
