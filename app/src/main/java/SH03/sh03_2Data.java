package SH03;

import java.util.ArrayList;

/**
 * Created by aliabbasjaffri on 28/07/15.
 */
public class sh03_2Data
{
    String heading;
    public ArrayList<sh03_2aData> data;

    public sh03_2Data(String heading, ArrayList<sh03_2aData> datas)
    {
        data = new ArrayList<>();

        this.heading = heading;
        this.data = datas;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
