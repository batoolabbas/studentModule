package com.studentmodule;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import SH1.SH1;
import SH2.*;

/**
 * Created by aliabbasjaffri on 08/07/15.
 */
public class MyPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private VideoView videoView;
    private Button videoPlayButton;
    private Button tutorCardButton;
    MediaController media;

    public static MyPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyPageFragment fragment = new MyPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        ViewPagerAdapter.studentPortalActivity.setToolbar( "My Page" , false );

        videoView = (VideoView) view.findViewById(R.id.videoView);
        media = new MediaController(getActivity());
        videoView.setMediaController(media);

        videoPlayButton = (Button) view.findViewById(R.id.videoPlayButton);
        videoPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new backgroundVideoLoader().execute("http://www.ebookfrenzy.com/android_book/movie.mp4");
                videoPlayButton.setVisibility(View.INVISIBLE);
            }
        });

        final Fragment fragment = new SH1();

        tutorCardButton = (Button) view.findViewById(R.id.tutorCardButton);
        tutorCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

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
                videoView.setVideoURI(uri[0]);
                videoView.requestFocus();

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer arg0)
                    {
                        videoView.start();
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