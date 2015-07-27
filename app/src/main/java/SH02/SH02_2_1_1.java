package SH02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH02_2_1_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH02_2_1_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView LV;
    private sh02211ArrayAdapter adaptor;
    private ArrayList<sh02_2_1_1Data> items = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH02_2_1_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH02_2_1_1 newInstance(String param1, String param2) {
        SH02_2_1_1 fragment = new SH02_2_1_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH02_2_1_1() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh02_2_1_1, container, false);

        items.add(new sh02_2_1_1Data(R.drawable.jane , "Jane" ));
        items.add(new sh02_2_1_1Data(R.drawable.jane , "Peter" ));
        items.add(new sh02_2_1_1Data(R.drawable.jane , "Martin" ));
        items.add(new sh02_2_1_1Data(R.drawable.jane , "Andrew" ));

        adaptor = new sh02211ArrayAdapter(getActivity() , R.layout.layout_sh02_2_1_1_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh02_2_1_1ListView);
        LV.setAdapter(adaptor);

        return view;
    }


}
