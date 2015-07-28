package SH01;

/**
 * Created by aliabbasjaffri on 29/07/15.
 */
public class sh01Data
{
    int tutorImage;
    String tutorName;
    Boolean availability;

    public sh01Data(int tutorImage, String tutorName, Boolean availability) {
        this.tutorImage = tutorImage;
        this.tutorName = tutorName;
        this.availability = availability;
    }

    public int getTutorImage() {
        return tutorImage;
    }

    public void setTutorImage(int tutorImage) {
        this.tutorImage = tutorImage;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
