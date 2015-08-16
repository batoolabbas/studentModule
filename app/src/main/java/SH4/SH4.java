package SH4;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.studentmodule.R;
import com.studentmodule.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SH1.sh1_5Data;
import helper.Base64CODEC;
import utils.AppConfig;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SH4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SH4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private LinearLayout americanTab;
    private LinearLayout filipinoTab;

    private ImageView americanImage;
    private ImageView filipinoImage;

    private TextView americanTabText;
    private TextView filipinoTabText;

    Bitmap temp;
    boolean flag = false;

    private boolean[] tabs = new boolean[2];

    private ListView LV;
    private sh4ArrayAdapter adaptor;
    private ArrayList<sh4Data> items = new ArrayList<>();

    String profilePictureURL = "http://www.monstertutors.com/upfiles/teacher/";
    public static String profilePicturePath = "/mt_profilepic.jpg";


    // TODO: Rename and change types and number of parameters
    public static SH4 newInstance() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        adaptor = new sh4ArrayAdapter(getActivity(), R.layout.layout_sh4_custom_row, items);

        LV.setAdapter(adaptor);
        tabs[0] = true;
        tabs[1] = false;

        //nation id= 4
        //nation id = 6

        americanTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabs[0] == false) {
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
            public void onClick(View v) {
                if (tabs[1] == false) {
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

    private void buttonDisable() {
        if (tabs[0] == true) {
            filipinoTab.setEnabled(false);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 2.5s = 2500ms
                    filipinoTab.setEnabled(true);
                }
            }, 2500);

        } else if (tabs[1] == true) {
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

    private void getAmericanTutorsData() {
        items.clear();

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

                    for (int i = 0; i < 4; i++) {
                        String name = jObj.getJSONObject(getString(i)).getString("name");

                        String json_photo = jObj.getJSONObject(getString(i)).getString("photo");

                        if (!json_photo.isEmpty())           // Means file exists on remote db
                            new DownloadFile().execute(profilePictureURL + json_photo, profilePicturePath);


                        items.add(new sh4Data(R.drawable.tutor_profile_badge, temp, name, R.drawable.country));
                        //                       }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //exception handling for failing to get teacher data
            }

        }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", tag_string_req);
                params.put("country", "USA");
                return params;
            }
        };
    }

    private void getFilipinoTutorsData() {
        items.clear();
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

                    for (int i = 0; i < 4; i++) {
                        String name = jObj.getJSONObject(getString(i)).getString("name");

                        String json_photo = jObj.getJSONObject(getString(i)).getString("photo");

                        if (!json_photo.isEmpty())           // Means file exists on remote db
                            new DownloadFile().execute(profilePictureURL + json_photo, profilePicturePath);


                        items.add(new sh4Data(R.drawable.tutor_profile_badge, temp, name, R.drawable.country));
                        //                       }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //exception handling for failing to get teacher data
            }

        }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", tag_string_req);
                params.put("country", "Philippines");
                return params;
            }
        };
    }

    private class DownloadFile extends AsyncTask<String, String, String> {

        String file_path;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog_downloader.setMax(33);
            //           alertTitleTv.setText("Downloading data...");
//            pDialog_downloader.setCustomTitle(titleView);
//            showDialog(pDialog_downloader);

        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output;

                file_path = f_url[1];
                // Output stream to write file
                output = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + file_path);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
//                    publishProgress("" + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
//            pDialog_downloader.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * *
         */
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            // Displaying downloaded image into image view
            // Reading image path from sdcard

            //          if(file_type.compareTo(DOWNLOAD_FILE_PICTURE) == 0) {

            String imagePath = Environment.getExternalStorageDirectory().toString() + file_path;

            temp = BitmapFactory.decodeFile(imagePath);
            temp = Base64CODEC.getResizedBitmap(temp, 800);
            //           try {
//                OutputStream imageOuputStream = new FileOutputStream(imagePath);
//                temp.compress(Bitmap.CompressFormat.JPEG, 50, imageOuputStream);

//            } catch (FileNotFoundException e) {

//            }
            temp = BitmapFactory.decodeFile(imagePath);
            //UserData.profile_pic_path = imagePath;
            //if(count==0) {
//
//            }
//            updateDB(imagePath);

        }
    }
}
