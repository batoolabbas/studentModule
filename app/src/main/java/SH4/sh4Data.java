package SH4;

/**
 * Created by aliabbasjaffri on 03/08/15.
 */
public class sh4Data
{
    int badgeImage;
    int profilePicture;
    String tutorName;
    int tutorCountry;

    public sh4Data(int badgeImage, int profilePicture, String tutorName, int tutorCountry) {
        this.badgeImage = badgeImage;
        this.profilePicture = profilePicture;
        this.tutorName = tutorName;
        this.tutorCountry = tutorCountry;
    }

    public int getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(int badgeImage) {
        this.badgeImage = badgeImage;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getTutorCountry() {
        return tutorCountry;
    }

    public void setTutorCountry(int tutorCountry) {
        this.tutorCountry = tutorCountry;
    }
}
