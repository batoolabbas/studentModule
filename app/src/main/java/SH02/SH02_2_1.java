package SH02;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studentmodule.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH02_2_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH02_2_1 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button monday;
    Button tuesday;
    Button wednesday;
    Button thursday;
    Button friday;
    Button saturday;
    Button findMatchingTutors;

    Drawable img;

    Boolean [] buttonClicked;
    Boolean [] selectedDays;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH02_2_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH02_2_1 newInstance(String param1, String param2) {
        SH02_2_1 fragment = new SH02_2_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH02_2_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        img = getActivity().getResources().getDrawable( R.drawable.week_select_icon );
        selectedDays = new Boolean[7]; //monday tuesday wednesday thursday friday saturday sunday
        buttonClicked = new Boolean[7];  //monday tuesday wednesday thursday friday saturday sunday

        for( int i = 0; i < 7; i++)
            buttonClicked[i] = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh02_2_1, container, false);

        monday = (Button) view.findViewById(R.id.sh02_2_1MondayButton);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[0])
                {
                    monday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    monday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[0] = true;
                }
                else if( buttonClicked[0] )
                {
                    monday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    monday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[0] = false;
                }
                selectedDays[0] = buttonClicked[0];
            }
        });

        tuesday = (Button) view.findViewById(R.id.sh02_2_1TuesdayButton);
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[1])
                {
                    tuesday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    tuesday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[1] = true;
                }
                else if( buttonClicked[1] )
                {
                    tuesday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    tuesday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[1] = false;
                }
                selectedDays[1] = buttonClicked[1];
            }
        });

        wednesday = (Button) view.findViewById(R.id.sh02_2_1WednesdayButton);
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[2])
                {
                    wednesday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    wednesday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[2] = true;
                }
                else if( buttonClicked[2] )
                {
                    wednesday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    wednesday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[2] = false;
                }
                selectedDays[2] = buttonClicked[2];
            }
        });

        thursday = (Button) view.findViewById(R.id.sh02_2_1ThursdayButton);
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[3])
                {
                    thursday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    thursday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[3] = true;
                }
                else if( buttonClicked[3] )
                {
                    thursday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    thursday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[3] = false;
                }
                selectedDays[3] = buttonClicked[3];
            }
        });

        friday = (Button) view.findViewById(R.id.sh02_2_1FridayButton);
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[4])
                {
                    friday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    friday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[4] = true;
                }
                else if( buttonClicked[4] )
                {
                    friday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    friday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[4] = false;
                }
                selectedDays[4] = buttonClicked[4];
            }
        });

        saturday = (Button) view.findViewById(R.id.sh02_2_1SaturdayButton);
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!buttonClicked[5])
                {
                    saturday.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                    saturday.setTextColor(Color.parseColor("#FFFFFF"));
                    buttonClicked[5] = true;
                }
                else if( buttonClicked[5] )
                {
                    saturday.setCompoundDrawablesWithIntrinsicBounds( null, null, null, null);
                    saturday.setTextColor(Color.parseColor("#000000"));
                    buttonClicked[5] = false;
                }
                selectedDays[5] = buttonClicked[5];
            }
        });

        final Fragment fragment = new SH02_2_1_1();

        findMatchingTutors = (Button) view.findViewById(R.id.sh02_2_1SearchMatchingTutorsButton);
        findMatchingTutors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack("").commit();
            }
        });





        return view;
    }


}
