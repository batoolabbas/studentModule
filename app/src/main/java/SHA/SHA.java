package SHA;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.studentmodule.R;
import com.studentmodule.StudentPortalActivity;
import com.studentmodule.TeacherProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SHA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SHA extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String tutorName = "param1";
    private static final String tutorVideoLink = "param2";
    private static final String tutorRating = "param3";

    // TODO: Rename and change types of parameters

    private String tutorNameParam;
    private String tutorVideoLinkParam;
    private int tutorRatingParam;

    private TextView tutorOfTheMonthText;
    private TextView tutorOfTheMonthName;
    private TextView tutorOfTheMonthRating;
    private LinearLayout tutorOfTheMonthSelectButton;

    private VideoView tutorOfTheMonthVideoView;
    private ImageView tutorOfTheMonthVideoPlayButton;

    private MediaController media;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SHA.
     */
    // TODO: Rename and change types and number of parameters
    public static SHA newInstance(String param1, String param2 , int param3)
    {
        SHA fragment = new SHA();
        Bundle args = new Bundle();
        args.putString(tutorName, param1);
        args.putString(tutorVideoLink, param2);
        args.putInt(tutorRating, param3);
        fragment.setArguments(args);
        return fragment;
    }

    public SHA() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tutorNameParam = getArguments().getString(tutorName);
            tutorVideoLinkParam = getArguments().getString(tutorVideoLink);
            tutorRatingParam = getArguments().getInt(tutorRating);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh, container, false);

        tutorOfTheMonthText = (TextView) view.findViewById(R.id.sponsored_tutor_month_txt);
        tutorOfTheMonthText.setText("For the month of June");

        tutorOfTheMonthName = (TextView) view.findViewById(R.id.tutorOfTheMonthName);
        tutorOfTheMonthName.setText(tutorNameParam);

        tutorOfTheMonthRating = (TextView) view.findViewById(R.id.tutorOfTheMonthRating);
        tutorOfTheMonthRating.setText("" + tutorRatingParam);

        tutorOfTheMonthSelectButton = (LinearLayout) view.findViewById(R.id.tutorSelectButton);
        tutorOfTheMonthSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText( getActivity() , "Button Clicked" , Toast.LENGTH_SHORT ).show();
                Intent i = new Intent( getActivity() , TeacherProfileActivity.class);
                i.putExtra("teacherName" , tutorNameParam);
                startActivity(i);
            }
        });

        tutorOfTheMonthVideoPlayButton = (ImageView) view.findViewById(R.id.sponsored_tutor_video_play_button);
        tutorOfTheMonthVideoPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tutorOfTheMonthVideoPlayButton.setVisibility(v.INVISIBLE);
                //new backgroundVideoLoader().execute( tutorVideoLinkParam );
                new backgroundVideoLoader().execute( "http://www.ebookfrenzy.com/android_book/movie.mp4");

            }
        });

        tutorOfTheMonthVideoView = (VideoView) view.findViewById(R.id.tutorOfTheMonthVideoView);
        media = new MediaController(getActivity());
        tutorOfTheMonthVideoView.setMediaController(media);

        return view;
    }

    public class backgroundVideoLoader extends AsyncTask<String, Uri, Void>
    {
        ProgressDialog dialog;

        @Override
        protected Void doInBackground(String... params)
        {
            try
            {
                Uri uri = Uri.parse(params[0]);

                publishProgress(uri);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(final Uri... uri)
        {
            try
            {
                tutorOfTheMonthVideoView.setVideoURI(uri[0]);
                tutorOfTheMonthVideoView.requestFocus();

                tutorOfTheMonthVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer arg0)
                    {
                        tutorOfTheMonthVideoView.start();
                        dialog.dismiss();
                    }
                });


            } catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }

        protected void onPreExecute()
        {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, Please Wait...");
            dialog.setCancelable(true);
            dialog.show();
        }
    }


}
