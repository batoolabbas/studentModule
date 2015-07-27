package SH2;

/**
 * Created by aliabbasjaffri on 26/07/15.
 */
public class sh2_3Data
{
    int tutorImage;
    String tutorName;
    int tutorCountry;

    public sh2_3Data(int tutorImage, String tutorName, int tutorCountry)
    {
        this.tutorImage = tutorImage;
        this.tutorName = tutorName;
        this.tutorCountry = tutorCountry;
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

    public int getTutorCountry() {
        return tutorCountry;
    }

    public void setTutorCountry(int tutorCountry) {
        this.tutorCountry = tutorCountry;
    }
}
