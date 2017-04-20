package nakthon.soraya.calldriver;

/**
 * Created by DARK on 17/4/2560.
 */

public class MyConstant {


    private String urlGetCall = "http://woodriverservice.com/Android/getCall.php";
    private String urlGetPassenger = "http://woodriverservice.com/Android/getPassenger.php";
    private String urlGetPassengerWherePhone = "http://woodriverservice.com/Android/getPassengerWherePhone.php";
    private String urlGetLocation = "http://woodriverservice.com/Android/getLocation.php";


    private String[] loginStrings = new String[]{"id", "Name", "User", "Password"};
    private String[] passengerColumnStrings = new String[]{"id", "Name", "Phone"};
    private String[] locationColumnStrings = new String[]{"id", "Name", "Lat", "Lng"};

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
