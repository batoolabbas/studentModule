package SH2;

/**
 * Created by aliabbasjaffri on 25/07/15.
 */
public class sh2_1Data
{
    String columnOne;
    String columnTwo;
    int columnThree;

    public sh2_1Data(String columnOne, String columnTwo, int columnThree)
    {
        this.columnOne = columnOne;
        this.columnTwo = columnTwo;
        this.columnThree = columnThree;
    }

    public String getColumnOne() {
        return columnOne;
    }

    public void setColumnOne(String columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return columnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
    }

    public int getColumnThree() {
        return columnThree;
    }

    public void setColumnThree(int columnThree) {
        this.columnThree = columnThree;
    }
}
