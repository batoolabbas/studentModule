package helper;

/**
 * Created by Murad on 7/5/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.studentmodule.SignUp;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_monster";

    // Login table name
    private static final String TABLE_MEMBER = "tbl_member";

    // Login Table Columns names

    //private static final String KEY_UID = "uid";
    public static SQLiteHandler instance = null;
    private static final String KEY_ID = "userid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "pwd";
    private static final String KEY_LEVEL = "level";



    private static final String KEY_CREATED_AT = "reg_date";
    private static final String KEY_hp1 = "hp1";
    private static final String KEY_hp2 = "hp2";
    private static final String KEY_hp3 = "hp3";
    private static final String KEY_SON = "son";
    private static final String KEY_POINT = "point";
    private static final String KEY_RECEIVE = "receive";

//    public static final String APPROVED = "approved";
    public static final String FIRST_LOGIN = "first_login";
    public static final String IMAGE_PATH = "image_path";
    public static final String VIDEO_PATH = "video_path";
    public static final String AUDIO_PATH = "audio_path";
    public static final String DETAIL = "detail";
    public static final String PAYMENT = "payment";
    public static final String GMT = "gmt";
    public static final String COUNTRY_CODE = "country_code";
    public static final String PHONE_NUMBER = "phone";
    public static final String NAME = "name";

    public static final String SEQUENCE_NATION = "sequence_nation";
    public static final String SEQUENCE_NATION_STAY = "sequence_nation_stay";
    public static final String SKYPE_ID = "skypeid";
    public static final String NATIONALITY = "nationality";
    public static final String EMAIL = "email";
    public static final String BADGE = "badge";
    public static final String COUNTRY = "country";
    public static final String ORIGINAL_DP_BITMAP = "original_dp";
    public static final String BLURRED_DP_BITMAP = "blurred_dp";
    public static final String TERMS_CHECK = "terms_check";




    public  static  synchronized SQLiteHandler getInstance(Context c){
        if (instance != null)
            return instance;
        else {
            instance = new SQLiteHandler(c);
            return instance;
        }
    }
    private SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_USER_DATA_TABLE = "CREATE TABLE " + TABLE_MEMBER + "(seq INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + APPROVED + " VARCHAR(1) DEFAULT n,"
                + FIRST_LOGIN + " VARCHAR(1) DEFAULT y,"
                + NAME + " VARCHAR(20) DEFAULT null,"

                + SKYPE_ID + " VARCHAR(20) DEFAULT null,"
                + VIDEO_PATH + " VARCHAR(100) DEFAULT null,"
                + AUDIO_PATH + " VARCHAR(100) DEFAULT null,"
                + DETAIL + " VARCHAR(250) DEFAULT null,"
                + PAYMENT + " VARCHAR(50) DEFAULT null,"
                + GMT + " VARCHAR(50) DEFAULT null,"
                + COUNTRY_CODE + " VARCHAR(2) DEFAULT null,"
                + PHONE_NUMBER + " VARCHAR(15) DEFAULT null,"
                + SEQUENCE_NATION + " VARCHAR(15) DEFAULT null,"
                + SEQUENCE_NATION_STAY + " VARCHAR(15) DEFAULT null,"
                + NATIONALITY + " VARCHAR(15) DEFAULT null,"
                + EMAIL + " VARCHAR(25) DEFAULT null,"
                + BADGE + "TEXT DEFAULT null,"
                + COUNTRY + "TEXT DEFAULT null,"
                + ORIGINAL_DP_BITMAP + " TEXT DEFAULT null,"
                + BLURRED_DP_BITMAP + " TEXT DEFAULT null,"
                + TERMS_CHECK + " TEXT DEFAULT null"
                + ")";

        Log.e(TAG, CREATE_USER_DATA_TABLE);


        db.execSQL(CREATE_USER_DATA_TABLE);

        Log.d(TAG, "Database tables created");




    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
//    public void addUser(String name, String userid, String pwd, String level, String reg_date,
//                        String hp1, String hp2, String hp3, String son, String email, String point, String receive) {



    public void setFirstLoginFalse(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIRST_LOGIN, "n");
        db.update(TABLE_MEMBER, cv, FIRST_LOGIN + " = 'y'", null);
        //db.close();
    }

//    public void setApprovedTrue(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(APPROVED, "y");
//        db.update(TABLE_MEMBER, cv, APPROVED + " = 'n'", null);
//       // db.close();
//    }

    public void addUser(SignUp.UserDetails ud){

        SQLiteDatabase db = this.getWritableDatabase();
        if (getUserDetails().isEmpty()) {
            ContentValues values = new ContentValues();
//            values.put(APPROVED, "n");
            values.put(FIRST_LOGIN, "y");
            values.put(KEY_NAME,ud.getname());
            values.put(SKYPE_ID, ud.getSkype());
            values.put(PHONE_NUMBER,ud.getPhoneNum());
            values.put(EMAIL, ud.getEmail());
            values.put(KEY_LEVEL,ud.getEnglishLvl());

            long id = db.insert(TABLE_MEMBER, null, values);
            Log.d(TAG, "New user inserted into sqlite: " + id);
        }
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_MEMBER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Move to first row


        if (cursor!= null)
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.d("DB",cursor.getString(0));
            user.put("approved", cursor.getString(1));
            user.put("first_login", cursor.getString(2));
            user.put("name", cursor.getString(3));
            user.put("skypeid", cursor.getString(4));
            user.put("image_path", cursor.getString(5));
            user.put("video_path", cursor.getString(6));
            user.put("audio_path", cursor.getString(7));
            user.put("detail", cursor.getString(8));
            //user.put("payment", cursor.getString(8));
            user.put("gmt", cursor.getString(10));
            user.put(SQLiteHandler.ORIGINAL_DP_BITMAP,cursor.getString(17));
            user.put(SQLiteHandler.BLURRED_DP_BITMAP,cursor.getString(18));
            user.put(SQLiteHandler.TERMS_CHECK,cursor.getString(19));

//            user.put("country_code", cursor.getString(10));
//            user.put("phone_number", cursor.getString(11));
//            user.put("sequence_nation", cursor.getString(12));
//            user.put("sequence_nation_stay", cursor.getString(13));


            cursor.close();
        }

        //db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }


    public void updateUser(SignUp.UserDetails ud)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,ud.getname());
        cv.put(SKYPE_ID, ud.getSkype());
        cv.put(PHONE_NUMBER,ud.getPhoneNum());
        cv.put(EMAIL, ud.getEmail());
        cv.put(KEY_LEVEL,ud.getEnglishLvl());

        db.update(TABLE_MEMBER, cv, null, null);

    }

    public void updateField(String field,String value)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(field, value);
        db.update(TABLE_MEMBER, cv, null, null);

    }

    public void updateField(String field,boolean value)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(field, value);
        db.update(TABLE_MEMBER, cv, null, null);

    }

    /**
     * Getting user login status return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEMBER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        //db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_MEMBER, null, null);

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
