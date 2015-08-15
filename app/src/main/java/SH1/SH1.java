package SH1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import SH4.*;
import helper.Base64CODEC;
import helper.SQLiteHandler;
import utils.AppConfig;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SH1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SH1#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SH1 extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String profilePictureURL="http://www.monstertutors.com/upfiles/teacher/";
    public static String profilePicturePath = "/mt_profilepic.jpg";

    private static final String tutorIDParam = "param1";
    private static final String tutorNameParam = "param2";
    private static final String tutorRankParam = "param3";
    private static final String tutorURLParam = "param4";

    // TODO: Rename and change types of parameters
    private String mtutorName;
    private String mtutorID;
    private int mtutorRank;
    private String mtutorURL;

    public static String profile_pic_path;

    public static Drawable original_dp;
    public static Drawable blurred_dp;

    public static Bitmap bitmap_original_dp;
    public static Bitmap bitmap_blurred_dp;

    LinearLayout blurredBackground;
    LinearLayout dpContainer;
    LinearLayout profilePicture;
    ImageView profilePictureBadge;
    ImageView countryPicture;
    TextView profileName;
    TextView profileDetail;
    TextView universityName;
    TextView mTitle;
    Toolbar toolbar;
    Button video;
    Button audio;
    Button button3;
    Button button4;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SH1.
     */
    // TODO: Rename and change types and number of parameters
    public static SH1 newInstance(String param1, String param2, int param3, String param4)
    {
        SH1 fragment = new SH1();
        Bundle args = new Bundle();
        args.putString(tutorIDParam, param1);
        args.putString(tutorNameParam, param2);
        args.putInt(tutorRankParam,param3);
        args.putString(tutorURLParam,param4);
        fragment.setArguments(args);
        return fragment;
    }

    public SH1()
    {
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

        bitmap_original_dp = null;
        bitmap_blurred_dp = null;
        profile_pic_path = null;
        original_dp = null;
        blurred_dp = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sh1, container, false);

        final String tag_string_req = "teacherProfile";

        blurredBackground = (LinearLayout) view.findViewById(R.id.sh1TopContainer);

        dpContainer = (LinearLayout) view.findViewById(R.id.sh1DisplayImageContainer);
        profilePicture = (LinearLayout) view.findViewById(R.id.sh1ProfileImage);

        profilePictureBadge = (ImageView) view.findViewById(R.id.sh1ProfileBadge);
        countryPicture = (ImageView) view.findViewById(R.id.sh1ProfileCountry);

        profileName = (TextView) view.findViewById(R.id.sh1ProfileName);
        profileDetail = (TextView) view.findViewById(R.id.sh1PersonalDetails);
        universityName = (TextView) view.findViewById(R.id.sh1UniversityName);

        profileName.setText(mtutorName);

        video = (Button) view.findViewById(R.id.sh1VideoButton);
        audio = (Button) view.findViewById(R.id.sh1AudioButton);
        button3 = (Button) view.findViewById(R.id.sh1Button3);
        button4 = (Button) view.findViewById(R.id.sh1Button4);

        if( profile_pic_path != null && !profile_pic_path.isEmpty() )
        {
            ViewTreeObserver vto = dpContainer.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    if (original_dp == null) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.TUTOR_API_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);

                                            boolean error = jsonObject.getBoolean("error");
                                            if (!error) {
                                                String json_photo = jsonObject.getString("photo");
                                                profileDetail.setText(jsonObject.getString("ment") + jsonObject.getString("ment3"));

                                                SQLiteHandler db = SQLiteHandler.getInstance(getActivity().getApplicationContext());

                                                File profilephotoF = new File(Environment.getExternalStorageDirectory().toString() + profilePicturePath);
                                                if (profilephotoF.exists()) {
                                                    profilephotoF.delete();
                                                }
                                                if (!json_photo.isEmpty())           // Means file exists on remote db
                                                    new DownloadFile().execute(profilePictureURL + json_photo, profilePicturePath);

                                            }

                                        } catch (JSONException e) {

                                        }
                                    }
                                }, new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                //some error occurred
                            }
                        }) {
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("tag", tag_string_req);
                                params.put("userID", mtutorID);
                                return params;
                            }
                        };

                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap_original_dp, dpContainer.getWidth(), dpContainer.getHeight(), false);
                        Drawable drawable_dp = new BitmapDrawable(getResources(), scaledBitmap);
                        original_dp = drawable_dp;
                    }
                    if (profile_pic_path != null && !profile_pic_path.isEmpty()) {
                        ExifInterface ei = null;
                        try {
                            ei = new ExifInterface(profile_pic_path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                ExifInterface.ORIENTATION_UNDEFINED);

                        profilePicture.setVisibility(View.VISIBLE);

                        int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            profilePicture.setBackgroundDrawable(original_dp);
                        } else {
                            profilePicture.setBackground(original_dp);
                        }

                        ViewTreeObserver obs = dpContainer.getViewTreeObserver();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            obs.removeOnGlobalLayoutListener(this);
                        } else {
                            obs.removeGlobalOnLayoutListener(this);
                        }
                        if (blurred_dp == null) {
                            Bitmap blurredBitmap = Bitmap.createScaledBitmap(bitmap_original_dp, 5, 5, false);
                            bitmap_blurred_dp = blurredBitmap;

                            //SQLiteHandler db = SQLiteHandler.getInstance(getActivity().getApplicationContext());
                            //db.updateField(SQLiteHandler.BLURRED_DP_BITMAP, Base64CODEC.convertToBase64(UserData.bitmap_blurred_dp));

                            blurredBitmap = Bitmap.createScaledBitmap(blurredBitmap, blurredBackground.getWidth(), blurredBackground.getHeight(), true);
                            Drawable blurredDrawable = new BitmapDrawable(getResources(), blurredBitmap);
                            blurred_dp = blurredDrawable;
                        }

                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            blurredBackground.setBackgroundDrawable(blurred_dp);
                        } else {
                            blurredBackground.setBackground(blurred_dp);
                        }
                    }

                }
            });

        }

//        blurredBackground.setBackgroundResource(R.drawable.jane);
//        profilePicture.setBackgroundResource(R.drawable.jane);
        if(mtutorRank>0)
        {
            profilePictureBadge.setBackgroundResource(R.drawable.tutor_profile_badge);
        }

        countryPicture.setBackgroundResource(R.drawable.country);

        final Fragment fragment = new SH1_5();

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.teacherProfile, SH4.newInstance() ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.teacherProfile, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("").commit();
            }
        });

        return view;
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

                file_path =f_url[1];
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

            Bitmap bitmap_orignal_dp = BitmapFactory.decodeFile(imagePath);
            bitmap_orignal_dp = Base64CODEC.getResizedBitmap(bitmap_orignal_dp, 800);
            try {
                OutputStream imageOuputStream = new FileOutputStream(imagePath);
                bitmap_orignal_dp.compress(Bitmap.CompressFormat.JPEG, 50, imageOuputStream);

            } catch (FileNotFoundException e) {

            }
            bitmap_orignal_dp = BitmapFactory.decodeFile(imagePath);
            //UserData.profile_pic_path = imagePath;
            //if(count==0) {
            String s = Base64CODEC.convertToBase64(bitmap_orignal_dp);
            //Log.d("BASE64 String",s.length()+"");
            //    updateDB(s);
            UserData.bitmap_original_dp = bitmap_orignal_dp;
        }
//            else if(count==2)
//            {
//                String s = Base64CODEC.convertToBase64(bitmap_orignal_dp);
//                //Log.d("BASE64 String",s.length()+"");
//                updateDB(s);
//                UserData.bitmap_blurred_dp = bitmap_orignal_dp;
//            }
//            else if(count==4)
//            {
//                UserData.bitmap_badge = bitmap_orignal_dp;
//            }
        //          else
//            {
//                UserData.bitmap_country = bitmap_orignal_dp;
//
//            }
//            updateDB(imagePath);

    }
}


