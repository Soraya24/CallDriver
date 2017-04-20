package nakthon.soraya.calldriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private String[] passengerStrings;
    private TextView nameTextView, phoneTextView;
    private TextView startTextView, destinationTextView;
    private ImageView startImageView, destinationImageView;
    private String tag = "20AprilV1";
    private String[] resultStrings;
    private LatLng centerLatLng, startLatLng, destinationLatLng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_map);

        //Initial View
        initialView();

        //Get Value From Intent
        getValueFromIntent();

        //Show View
        showView();

        //Setup Fragment Map
        setupFragmentMap();

        //Image Controller
        imageController();


    }   // Main Method

    private void imageController() {
        startImageView.setOnClickListener(MapsActivity.this);
        destinationImageView.setOnClickListener(MapsActivity.this);
    }

    private void showView() {
        nameTextView.setText(passengerStrings[1]);
        phoneTextView.setText(passengerStrings[2]);
    }

    private void initialView() {
        nameTextView = (TextView) findViewById(R.id.txtName);
        phoneTextView = (TextView) findViewById(R.id.txtPhone);
        startTextView = (TextView) findViewById(R.id.edtStart);
        destinationTextView = (TextView) findViewById(R.id.edtDestination);
        startImageView = (ImageView) findViewById(R.id.imvStart);
        destinationImageView = (ImageView) findViewById(R.id.imvDestination);

    }

    private void getValueFromIntent() {
        passengerStrings = getIntent().getStringArrayExtra("Passenger");
    }

    private void setupFragmentMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        centerLatLng = new LatLng(13.668087, 100.622625);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));



    }   // onMapReady

    @Override
    public void onClick(View view) {

        if (view == startImageView) {
            Intent intent = new Intent(MapsActivity.this, SearchLocationActivity.class);
            intent.putExtra("Index", 0);
            startActivityForResult(intent, 1000);
        }
        if (view == destinationImageView) {
            Intent intent = new Intent(MapsActivity.this, SearchLocationActivity.class);
            intent.putExtra("Index", 1);
            startActivityForResult(intent, 1001);
        }

    }   // onClick

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        resultStrings = data.getStringArrayExtra("Result");
        showTextAnMarker(requestCode, resultStrings);



    }   // onActivityResult

    private void showTextAnMarker(int requestCode, String[] resultStrings) {

        Log.d(tag, "requestCode ==> " + requestCode);
        for (int i=0;i<resultStrings.length;i++) {
            Log.d(tag, "resultString(" + i + ") ==> " + resultStrings[i]);
        }

        switch (requestCode) {
            case 1000:

                startTextView.setText(resultStrings[1]);

                break;
            case 1001:

                destinationTextView.setText(resultStrings[1]);

                break;
        }


    }   // showTextAnMarker
}   // Main Class
