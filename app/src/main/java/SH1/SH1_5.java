package SH1;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.studentmodule.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.AppConfig;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH1_5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH1_5 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String tutorIDParam = "param1";
    private static final String tutorNameParam = "param2";
    private static final String tutorRankParam = "param3";
    private static final String tutorURLParam = "param4";

    // TODO: Rename and change types of parameters
    private String mtutorID;
    private String mtutorName;
    private int mtutorRank;
    private String mtutorURL;

    private ListView LV;
    private sh15ArrayAdapter adaptor;
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
    public static SH1_5 newInstance(String param1, String param2, int param3,String param4) {
        SH1_5 fragment = new SH1_5();
        Bundle args = new Bundle();
        args.putString(tutorIDParam, param1);
        args.putString(tutorNameParam, param2);
        args.putInt(tutorRankParam,param3);
        args.putString(tutorURLParam,param4);
        
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
            mtutorID = getArguments().getString(tutorIDParam);
            mtutorName = getArguments().getString(tutorNameParam);
            mtutorRank = getArguments().getInt(tutorRankParam);
            mtutorURL = getArguments().getString(tutorURLParam);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1_5, container, false);

        final String tag_string_req = "getReviews";
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
                        String date = jObj.getJSONObject(getString(i)).getString("reg_date");
                        String type = "Regular";
                        String title = jObj.getJSONObject(getString(i)).getString("title");
                        String writerID = jObj.getJSONObject(getString(i)).getString("userid");
                        boolean b_Type=true;
                        String content = jObj.getJSONObject(getString(i)).getString("contents");
                        int star= jObj.getJSONObject(getString(i)).getInt("star");

                        if(type == "Trial") // true
                        {
                            b_Type = false;
                        }

                        items.add(new sh1_5Data(type, title , writerID + "/"+ date ,content, star ,b_Type ));

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
                params.put("tutorid",tutorIDParam);
                return params;
            }
        };

        adaptor = new sh15ArrayAdapter( getActivity() , R.layout.layout_sh1_5_custom_row , items );

        LV = (ListView) view.findViewById(R.id.sh1_5ListView);
        LV.setAdapter(adaptor);

        return view;
    }
}
