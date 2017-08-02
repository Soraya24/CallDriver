package nakthon.soraya.calldriver;

/**
 * Created by DARK on 17/4/2560.
 */

public class MyConstant {


    private String urlGetCall = "http://woodriverservice.com/Android/getCall.php";
    private String urlGetPassenger = "http://woodriverservice.com/Android/getPassenger.php";
    private String urlGetPassengerWherePhone = "http://woodriverservice.com/Android/getPassengerWherePhone.php";
    private String urlGetLocation = "http://woodriverservice.com/Android/getLocation.php";
    private String urlGetLocationWhereName = "http://woodriverservice.com/Android/getLocationWhereName.php";
    private String urlGetPassengerWhereID = "http://woodriverservice.com/Android/getPassengerWhereID.php";
    private String urlGetAllPureJom = "http://woodriverservice.com/Android/getPureJob.php";
    private String urlGetNameWhereID = "http://woodriverservice.com/Android/getNameWhereID.php";
    private String urlGetIdPassWhereID = "http://woodriverservice.com/Android/getIdPassWhereID.php";
    private String urlGetPureJobWhereID = "http://woodriverservice.com/Android/getPureJobWhereID.php";
    private String urlAddPureJob = "http://woodriverservice.com/Android/addPureJob.php";


    private String[] loginStrings = new String[]{"id", "Name", "User", "Password"};
    private String[] passengerColumnStrings = new String[]{"id", "Name", "Phone"};
    private String[] locationColumnStrings = new String[]{"id", "Name", "Lat", "Lng"};
    private String[] columnReportPureJobFragmentStrings = new String[]{"สำดับ", "ชื่อลูกค้า", "เบอร์", "จุดรับ", "จุดส่ง", "เวลา", "ราคา"};


    private String serverKeyString = "AIzaSyD_6HZwKgnxSOSkMWocLs4-2AViQuPBteQ";

    public String getUrlAddPureJob() {
        return urlAddPureJob;
    }

    public String getUrlGetPureJobWhereID() {
        return urlGetPureJobWhereID;
    }

    public String getUrlGetIdPassWhereID() {
        return urlGetIdPassWhereID;
    }

    public String getUrlGetNameWhereID() {
        return urlGetNameWhereID;
    }

    public String getUrlGetAllPureJom() {
        return urlGetAllPureJom;
    }

    public String getUrlGetPassengerWhereID() {
        return urlGetPassengerWhereID;
    }

    public String[] getColumnReportPureJobFragmentStrings() {
        return columnReportPureJobFragmentStrings;
    }

    public String getServerKeyString() {
        return serverKeyString;
    }

    public String getUrlGetLocationWhereName() {
        return urlGetLocationWhereName;
    }

    public String[] getLocationColumnStrings() {
        return locationColumnStrings;
    }

    public String getUrlGetLocation() {
        return urlGetLocation;
    }

    public String getUrlGetPassengerWherePhone() {
        return urlGetPassengerWherePhone;
    }

    public String getUrlGetPassenger() {
        return urlGetPassenger;
    }

    public String[] getPassengerColumnStrings() {
        return passengerColumnStrings;
    }

    public String[] getLoginStrings() {
        return loginStrings;
    }

    public String getUrlGetCall() {
        return urlGetCall;
    }
}   //Main Class
