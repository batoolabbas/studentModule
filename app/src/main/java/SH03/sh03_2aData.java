package SH03;

/**
 * Created by aliabbasjaffri on 28/07/15.
 */
public class sh03_2aData
{
    String tutorName;
    int tutorImage;
    String voiceLink;

    public sh03_2aData(String tutorName, int tutorImage, String voiceLink) {
        this.tutorName = tutorName;
        this.tutorImage = tutorImage;
        this.voiceLink = voiceLink;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getTutorImage() {
        return tutorImage;
    }

    public void setTutorImage(int tutorImage) {
        this.tutorImage = tutorImage;
    }

    public String getVoiceLink() {
        return voiceLink;
    }

    public void setVoiceLink(String voiceLink) {
        this.voiceLink = voiceLink;
    }
}
