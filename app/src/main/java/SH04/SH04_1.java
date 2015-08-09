package SH04;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH04_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH04_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String reviewText;
    private int revirewRating;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH04_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH04_1 newInstance(String param1, String param2) {
        SH04_1 fragment = new SH04_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH04_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh04_1, container, false);

        ViewPagerAdapter.studentPortalActivity.setToolbar("Review" , true);

        EditText editText = (EditText) view.findViewById(R.id.sh04_1ReviewEditText);
        reviewText = editText.getText().toString();

        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.sh04_1RatingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                // TODO Auto-generated method stub
                revirewRating = Math.round(rating);
                Toast.makeText(getActivity(), Float.toString(rating), Toast.LENGTH_SHORT).show();
            }

        });

        Button confirmButton = (Button) view.findViewById(R.id.sh04_1ConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Review Submit Confirmation!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
