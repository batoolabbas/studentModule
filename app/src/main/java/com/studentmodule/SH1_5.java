package com.studentmodule;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH1_5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_5 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView LV;
    private ArrayList <sh1_5Data> items = new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1_5.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1_5 newInstance(String param1, String param2) {
        SH1_5 fragment = new SH1_5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1_5() {
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
        View view = inflater.inflate(R.layout.fragment_sh1_5, container, false);

        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , true ));
        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , false ));
        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , true ));
        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , true ));
        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , true ));
        items.add(new sh1_5Data("hello" , "How are" , "you bhai" , true ));

        Log.i("Hello" , "in onCreateView SH1_5");

        sh1_5ArrayAdapter adapter = new sh1_5ArrayAdapter( getActivity().getApplicationContext() , R.layout.layout_sh1_5_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh1_5ListView);
        LV.setAdapter(adapter);

        return view;
    }


}
