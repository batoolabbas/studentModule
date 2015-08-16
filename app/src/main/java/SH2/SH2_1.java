package SH2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.studentmodule.R;
import com.studentmodule.ViewPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SHA.shAData;
import utils.AppConfig;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH2_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH2_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int total=0;

    private ListView LV;
    private sh21ArrayAdapter adaptor;
    private ArrayList<sh2_1Data> items = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH2_1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH2_1 newInstance(String param1, String param2) {
        SH2_1 fragment = new SH2_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SH2_1() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh2_1, container, false);

        clearAndAddToArray();

        ViewPagerAdapter.studentPortalActivity.setToolbar("My Points" , true);

        adaptor = new sh21ArrayAdapter( getActivity() , R.layout.layout_sh2_1_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh2_1ListView);
        LV.setAdapter(adaptor);

        Button totalButton = (Button) view.findViewById(R.id.sh2_1TotalButton);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        String totalText = "Total \t\t\t\t\t\t\t\t\t\t\t\t" + getString(total);

        totalButton.setText(totalText);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        clearAndAddToArray();
    }

    @Override
    public void onResume() {
        super.onResume();

        clearAndAddToArray();
    }

    void clearAndAddToArray()
    {
        if(!items.isEmpty())
            items.clear();

        final String tag_string_req = "getPoints";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConfig.TUTOR_API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                try {
                    JSONObject jObj = new JSONObject(s);
//                    boolean error =jObj.getBoolean("error");
//
//                    if(!error)
//                    {
                    for(int i=0;i<jObj.length();i++) {
                        int point = jObj.getJSONObject(getString(i)).getInt("point");
                        String date = jObj.getJSONObject(getString(i)).getString("point_date");
                        String type = jObj.getJSONObject(getString(i)).getString("flag_point_type");

                        if(type=="10")
                        {
                            type += ": Sign up";
                        }
                        else if(type=="20")
                        {
                            type += ": Class Review";
                        }
                        else if(type=="30")
                        {
                            type += ": Class Review";
                        }
                        else if(type=="40")
                        {
                            type += ": Question and Opinion";
                        }
                        else if(type=="50")
                        {
                            type +=": Class Review";
                        }
                        else if(type=="60")
                        {
                            type+=": Purchased points";
                        }
                        else if(type=="70")
                        {
                            type +=": Trial Class";
                        }
                        else if(type=="80")
                        {
                            type +=": Class Review";
                        }
                        else if(type=="90")
                        {
                            type +=": Class Review";
                        }

                        items.add(new sh2_1Data(type , date , point ));

                        //                       }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //exception handling for failing to get teacher data
            }

        }){

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            String userid = preferences.getString("SkypeID",null);
                protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("tag",tag_string_req);
                params.put("userid",userid);
                return params;
            }
        };

    }
}
