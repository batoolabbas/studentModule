package SH03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH03_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH03_2 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ExpandableListView eLV;
    private ArrayList<sh03_2aData> childItems1 = new ArrayList<>();
    private ArrayList<sh03_2aData> childItems2 = new ArrayList<>();
    private ArrayList<sh03_2aData> childItems3 = new ArrayList<>();
    private ArrayList<sh03_2Data> items = new ArrayList<>();
    private sh032ArrayAdapter adaptor;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH03_2.
     */
    // TODO: Rename and change types and number of parameters
    public static SH03_2 newInstance(String param1, String param2) {
        SH03_2 fragment = new SH03_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH03_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        childItems1.add( new sh03_2aData("Jane" , R.drawable.jane , "thisDownload") );
        childItems1.add(new sh03_2aData("Peter", R.drawable.jane, "thisDownload"));

        items.add(new sh03_2Data("My Recordings" , childItems1));

        childItems2.add(new sh03_2aData("Dickinson", R.drawable.jane, "thisDownload"));
        childItems2.add(new sh03_2aData("McBlabba", R.drawable.jane, "thisDownload"));

        items.add(new sh03_2Data("My Recordings(1)", childItems2));

        childItems3.add(new sh03_2aData("Bubba", R.drawable.jane, "thisDownload"));
        childItems3.add(new sh03_2aData("Wolverine", R.drawable.jane, "thisDownload"));

        items.add(new sh03_2Data("My Recordings(2)", childItems3));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh03_2, container, false);

        adaptor = new sh032ArrayAdapter(items , getActivity());
        eLV = (ExpandableListView) view.findViewById(R.id.sh03_2ExpandableListView);
        eLV.setAdapter(adaptor);

        return view;
    }


}
