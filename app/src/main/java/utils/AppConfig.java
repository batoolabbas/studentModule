package utils;

/**
 * Created by Murad on 7/5/2015.
 */
public class AppConfig {
    // Server user login url
   // public static String URL_LOGIN = "http://192.168.1.100/monster_tutors_API/";

    public static String IP_ADDRESS = "http://192.168.4.101/";
    public static String URL_LOGIN = IP_ADDRESS + "monster_tutors_API/";

    // Server user register url
//    public static String TUTOR_API_URL = "http://192.168.1.100/monster_tutors_API/";
    public static String TUTOR_API_URL = IP_ADDRESS + "monster_tutors_API/";

    //public static final String FILE_UPLOAD_URL = "http://192.168.1.100/monster_tutors_API/fileUpload.php";


    public static final String FILE_UPLOAD_URL = IP_ADDRESS + "monster_tutors_API/fileUpload.php";

    public static final String AUDIO_COMMENT_UPLOAD_URL = IP_ADDRESS + "monster_tutors_API/audioCommentUpload.php";

    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";


}