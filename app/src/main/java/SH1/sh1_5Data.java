package SH1;

/**
 * Created by aliabbasjaffri on 23/07/15.
 */
public class sh1_5Data
{
    String buttonInfo;
    String listInfo;
    String listDetail;
    Boolean buttonType; //true for regular,  false for trial

    public sh1_5Data(String buttonInfo, String listInfo, String listDetail, Boolean buttonType) {
        this.buttonInfo = buttonInfo;
        this.listInfo = listInfo;
        this.listDetail = listDetail;
        this.buttonType = buttonType;
    }

    public String getButtonInfo() {
        return buttonInfo;
    }

    public void setButtonInfo(String buttonInfo) {
        this.buttonInfo = buttonInfo;
    }

    public String getListInfo() {
        return listInfo;
    }

    public void setListInfo(String listInfo) {
        this.listInfo = listInfo;
    }

    public String getListDetail() {
        return listDetail;
    }

    public void setListDetail(String listDetail) {
        this.listDetail = listDetail;
    }

    public Boolean getButtonType() {
        return buttonType;
    }

    public void setButtonType(Boolean buttonType) {
        this.buttonType = buttonType;
    }
}
