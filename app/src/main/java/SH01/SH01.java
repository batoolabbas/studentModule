package SH01;


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
 * Use the {@link SH01#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH01 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ListView LV;
    private sh01ArrayAdapter adaptor;
    private ArrayList<sh01Data> items = new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH01.
     */
    // TODO: Rename and change types and number of parameters
    public static SH01 newInstance() {
        SH01 fragment = new SH01();
        return fragment;
    }

    public SH01() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        items.add(new sh01Data(R.drawable.jane , "Jane" , true ));
        items.add(new sh01Data(R.drawable.jane , "Hamzus" , true ));
        items.add(new sh01Data(R.drawable.jane , "Big Brother" , false ));
        items.add(new sh01Data(R.drawable.jane , "Wolverine" , true ));
        items.add(new sh01Data(R.drawable.jane , "Gambit" , false ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh01, container, false);

        adaptor = new sh01ArrayAdapter(getActivity() , R.layout.layout_sh01_custom_row , items);

        LV = (ListView) view.findViewById(R.id.sh01ListView);
        LV.setAdapter(adaptor);

        return view;
    }


}
