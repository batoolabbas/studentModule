package SH02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studentmodule.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH02_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH02_2 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH02_2.
     */
    // TODO: Rename and change types and number of parameters
    public static SH02_2 newInstance( ) {
        SH02_2 fragment = new SH02_2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SH02_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh02_2, container, false);

        final Fragment fragment = new SH02_2_1();

        Button applyForNewClassesButton = (Button) view.findViewById(R.id.sh02_2Button);
        applyForNewClassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        return view;
    }


}
