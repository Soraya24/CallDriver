package nakthon.soraya.calldriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private String[] passengerStrings;
    private TextView nameTextView, phoneTextView;
    private TextView startTextView, destinationTextView;
    private ImageView startImageView, destinationImageView;


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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMapReady

    @Override
    public void onClick(View view) {

        if (view == startImageView) {
            Intent intent = new Intent(MapsActivity.this, SearchLocationActivity.class);
            intent.putExtra("Index", 0);
            startActivity(intent);
        }
        if (view == destinationImageView) {
            Intent intent = new Intent(MapsActivity.this, SearchLocationActivity.class);
            intent.putExtra("Index", 1);
            startActivity(intent);
        }

    }   // onClick
}   // Main Class
