package SH1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SH1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SH1#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SH1 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    LinearLayout blurredBackground;
    LinearLayout profilePicture;
    ImageView profilePictureBadge;
    ImageView countryPicture;
    TextView profileDetail;
    Button video;
    Button audio;
    Button button3;
    Button button4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1 newInstance(String param1, String param2)
    {
        SH1 fragment = new SH1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1, container, false);

        ViewPagerAdapter.studentPortalActivity.setToolbar( "SH1" , true );

        blurredBackground = (LinearLayout) view.findViewById(R.id.sh1TopContainer);
        profilePicture = (LinearLayout) view.findViewById(R.id.sh1ProfileImage);
        profilePictureBadge = (ImageView) view.findViewById(R.id.sh1ProfileBadge);
        countryPicture = (ImageView) view.findViewById(R.id.sh1ProfileCountry);
        profileDetail = (TextView) view.findViewById(R.id.sh1PersonalDetails);
        video = (Button) view.findViewById(R.id.sh1VideoButton);
        audio = (Button) view.findViewById(R.id.sh1AudioButton);
        button3 = (Button) view.findViewById(R.id.sh1Button3);
        button4 = (Button) view.findViewById(R.id.sh1Button4);


        blurredBackground.setBackgroundResource(R.drawable.jane);
        profilePicture.setBackgroundResource(R.drawable.jane);
        profilePictureBadge.setBackgroundResource(R.drawable.tutor_profile_badge);
        countryPicture.setBackgroundResource(R.drawable.country);

        final Fragment fragment = new SH1_5();

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }
}
