package SH4;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.studentmodule.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH4 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private LinearLayout americanTab;
    private LinearLayout filipinoTab;

    private ImageView americanImage;
    private ImageView filipinoImage;

    private TextView americanTabText;
    private TextView filipinoTabText;

    private Boolean [] tabs = new Boolean[2];

    private ListView LV;
    private sh4ArrayAdapter adaptor;
    private ArrayList<sh4Data> items = new ArrayList<>();

    // TODO: Rename and change types and number of parameters
    public static SH4 newInstance()
    {
        SH4 fragment = new SH4();
        return fragment;
    }

    public SH4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sh4, container, false);

        americanImage = (ImageView) view.findViewById(R.id.sh4AmericanTabImageView);
        filipinoImage = (ImageView) view.findViewById(R.id.sh4FilipinoTabImageView);
        filipinoImage.setImageResource(R.drawable.pill_off);

        americanTabText = (TextView) view.findViewById(R.id.sh4AmericanTabTextView);
        filipinoTabText = (TextView) view.findViewById(R.id.sh4FilipinoTabTextView);

        americanTab = (LinearLayout) view.findViewById(R.id.sh4AmericanTab);
        americanTab.setSelected(true);
        filipinoTab = (LinearLayout) view.findViewById(R.id.sh4FilipinoTab);
        filipinoTab.setSelected(false);

        LV = (ListView) view.findViewById(R.id.sh4ListView);
        getAmericanTutorsData();
        adaptor = new sh4ArrayAdapter(getActivity() , R.layout.layout_sh4_custom_row , items);

        LV.setAdapter(adaptor);
        tabs[0] = true;
        tabs[1] = false;

        americanTab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( tabs[0] == false )
                {
                    americanImage.setImageResource(R.drawable.eng);
                    filipinoImage.setImageResource(R.drawable.pill_off);

                    getAmericanTutorsData();
                    adaptor.notifyDataSetChanged();
                    buttonDisable();
                    tabs[0] = true;
                    tabs[1] = false;
                }
            }
        });

        filipinoTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if( tabs[1] == false )
                {
                    filipinoImage.setImageResource(R.drawable.pill);
                    americanImage.setImageResource(R.drawable.eng_off);
                    getFilipinoTutorsData();
                    adaptor.notifyDataSetChanged();
                    buttonDisable();
                    tabs[0] = false;
                    tabs[1] = true;
                }
            }
        });

        return view;
    }

    private void buttonDisable()
    {
        if( tabs[0] == true )
        {
            filipinoTab.setEnabled(false);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 2.5s = 2500ms
                    filipinoTab.setEnabled(true);
                }
            }, 2500);

        }
        else if( tabs[1] == true )
        {
            americanTab.setEnabled(false);

            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 2.5s = 2500ms
                    americanTab.setEnabled(true);
                }
            }, 2500);
        }
    }

    private void getAmericanTutorsData()
    {
        items.clear();
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Jane Roberts" , R.drawable.country  ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Jane Roberts" , R.drawable.country  ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Jane Roberts" , R.drawable.country  ));
    }

    private void getFilipinoTutorsData()
    {
        items.clear();
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Hamza Roberts" , R.drawable.country ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Hamza Roberts" , R.drawable.country  ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Hamza Roberts" , R.drawable.country  ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Hamza Roberts" , R.drawable.country  ));
        items.add(new sh4Data(R.drawable.tutor_profile_badge , R.drawable.jane , "Hamza Roberts" , R.drawable.country  ));
    }
}
