package nakthon.soraya.calldriver;

/**
 * Created by DARK on 17/4/2560.
 */

public class MyConstant {


    private String urlGetCall = "http://woodriverservice.com/Android/getCall.php";
    private String urlGetPassenger = "http://woodriverservice.com/Android/getPassenger.php";


    private String[] loginStrings = new String[]{"id", "Name", "User", "Password"};
    private String[] passengerColumnStrings = new String[]{"id", "Name", "Phone"};

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
