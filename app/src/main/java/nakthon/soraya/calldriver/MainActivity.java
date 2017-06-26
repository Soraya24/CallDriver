package nakthon.soraya.calldriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userEditText, passwordEditText;
    private Button button;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial View
        initialView();

        //Buttom Controller
        buttomController();


    }   // Main Method

    private void buttomController() {
        button.setOnClickListener(MainActivity.this);
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            //Get Value From Edit Text
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();
            MyAlert myAlert = new MyAlert(MainActivity.this);

            if (userString.equals("") || passwordString.equals("")) {
                //Have Space
                myAlert.generalDialog(R.mipmap.ic_user,
                        getResources().getString(R.string.title_HaveSpace),
                        getResources().getString(R.string.massage_HaveSpace));

            } else {
                checkUserPassword();
            }
        }

    }

    private void checkUserPassword() {

        String tag = "18AprilV1";
        boolean b = true;
        MyConstant myConstant = new MyConstant();
        String[] columnCallStrings = myConstant.getLoginStrings();
        String[] loginStrings = new String[columnCallStrings.length];
        MyAlert myAlert = new MyAlert(MainActivity.this);

        try {

            GetAllData getAllData = new GetAllData(MainActivity.this);
            getAllData.execute(myConstant.getUrlGetCall());
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString(columnCallStrings[2]))) {
                    b = false;
                    for (int i1=0;i1<columnCallStrings.length;i1++) {
                        loginStrings[i1] = jsonObject.getString(columnCallStrings[i1]);
                        Log.d(tag, "loginString(" + i1 + ") ==> " + loginStrings[i1]);
                    }   // for2
                }   // if
            }   // for

            if (b) {
                //User False
                myAlert.generalDialog(R.mipmap.ic_user,
                        getResources().getString(R.string.title_UserFalse),
                        getResources().getString(R.string.massage_UserFalse));
            } else if (passwordString.equals(loginStrings[3])) {
                //Password True
                Toast.makeText(MainActivity.this, "Welcome " + loginStrings[1],
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                intent.putExtra("Login", loginStrings);
                startActivity(intent);
                finish();
            } else {
                // Password False
                myAlert.generalDialog(R.mipmap.ic_password3,
                        getResources().getString(R.string.title_PasswordFalse),
                        getResources().getString(R.string.massage_PasswordFalse));
            }


        } catch (Exception e) {
            Log.d(tag, "e checkUser ==> " + e.toString());
        }
    }
}   // Main Class
