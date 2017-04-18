package nakthon.soraya.calldriver;

/**
 * Created by DARK on 17/4/2560.
 */

public class MyConstant {


    private String urlGetCall = "http://woodriverservice.com/Android/getCall.php";
    private String[] loginStrings = new String[]{"id", "Name", "User", "Password"};

    public String[] getLoginStrings() {
        return loginStrings;
    }

    public String getUrlGetCall() {
        return urlGetCall;
    }
}   //Main Class
