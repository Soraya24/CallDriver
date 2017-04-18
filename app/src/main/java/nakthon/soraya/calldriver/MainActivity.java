package nakthon.soraya.calldriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

            }
        }

    }
}   // Main Class
