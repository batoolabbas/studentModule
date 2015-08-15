package SHA;

/**
 * Created by aliabbasjaffri on 01/08/15.
 */
public class shAData
{
    String tutorName;
    String videoLink;
    int rating;
    String tutorID;


    public shAData(String tutorName, String videoLink, int rating, String id)
    {
        this.tutorName = tutorName;
        this.videoLink = videoLink;
        this.rating = rating;
        this.tutorID = id;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTutorID(){return tutorID;}
}
