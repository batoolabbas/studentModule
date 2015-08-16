package SH1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.studentmodule.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH1_5_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_5_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String buttonInfo = "param1";
    private static final String listTitle = "param2";
    private static final String listDetail = "param3";
    private static final String position = "param4";
    private static final String content = "param5";
    private static final String star = "param6";

    // TODO: Rename and change types of parameters
    private String mButtonInfo;
    private String mListInfo;
    private String mListDetail;
    private int mPosition;
    private int mstar;
    private String mContent;

    private ListView LV;
    private sh151ArrayAdapter adaptor;
    private ArrayList<sh1_5_1Data> items = new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1_5_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1_5_1 newInstance(String param1, String param2, String param3 , String param5 , int param6 , int param4)
    {
        SH1_5_1 fragment = new SH1_5_1();
        Bundle args = new Bundle();
        args.putString(buttonInfo, param1);
        args.putString(listTitle, param2);
        args.putString(listDetail, param3);
        args.putInt(position, param4);
        args.putString(content, param5);
        args.putInt(star,param6);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1_5_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mButtonInfo = getArguments().getString(buttonInfo);
            mListInfo = getArguments().getString(listTitle);
            mListDetail = getArguments().getString(listDetail);
            mPosition = getArguments().getInt(position);
            mContent = getArguments().getString(content);
            mstar = getArguments().getInt(star);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1_5_1, container, false);

        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.fragmentSH1_5_1Row);
        Button button = (Button) rl.findViewById(R.id.sh1_5Button);
        button.setText(mButtonInfo);
        TextView listInfo = (TextView) rl.findViewById(R.id.sh1_5ListInfo);
        listInfo.setText(mListInfo);
        TextView listDetail = (TextView) rl.findViewById(R.id.sh1_5ListDetail);
        listDetail.setText(mListDetail);
        TextView content = (TextView) view.findViewById(R.id.textView13);
        content.setText(mContent);

        RatingBar rb = (RatingBar) view.findViewById(R.id.sh1_5_1RatingBar);
        rb.setMax(5);
        rb.setNumStars(5);
        rb.setRating(mstar);

        items.add(new sh1_5_1Data("hello" , "How are you bhai"));
        items.add(new sh1_5_1Data("hello" , "How are you bhai"));
        items.add(new sh1_5_1Data("hello" , "How are you bhai"));

        adaptor = new sh151ArrayAdapter( getActivity() , R.layout.layout_sh1_5_1_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh1_5_1ListView);
        LV.setAdapter(adaptor);

        return view;
    }


}
