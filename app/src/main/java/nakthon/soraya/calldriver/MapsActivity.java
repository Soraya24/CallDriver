package nakthon.soraya.calldriver;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private GoogleMap mMap;
    private String[] passengerStrings;
    private TextView nameTextView, phoneTextView;
    private TextView startTextView, destinationTextView,
            distanceTextView, priceTextView,
            dateTextView, timeTextView;
    private ImageView startImageView, destinationImageView,
            dateImageView, timeImageView;
    private String tag = "20AprilV1", distanceString,
            dateString, timeString, remarkString;
    private String[] resultStrings;
    private LatLng centerLatLng, startLatLng, destinationLatLng;
    private double startLatADouble = 0, startLngADouble = 0,
            destinationLatADouble = 0, destinationLngADouble = 0;
    private MarkerOptions startMarker, destinationMarker;
    private MyConstant myConstant;
    private int intDay, intMonth, intYear, hourAnInt, minusAnInt, secondAnInt;
    private Button cancleButton, okButton;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_map);

        //Initial View
        initialView();

        //Get Value From Intent
        getValueFromIntent();

        //Get Curent Date and Time
        getCurrentDataTime();

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
        dateImageView.setOnClickListener(MapsActivity.this);
        timeImageView.setOnClickListener(MapsActivity.this);
        cancleButton.setOnClickListener(MapsActivity.this);
        okButton.setOnClickListener(MapsActivity.this);
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
        distanceTextView = (TextView) findViewById(R.id.txtDistance);
        priceTextView = (TextView) findViewById(R.id.txtPrice);
        dateImageView = (ImageView) findViewById(R.id.imvDate);
        timeImageView = (ImageView) findViewById(R.id.imvTime);
        dateTextView = (TextView) findViewById(R.id.txtDate);
        timeTextView = (TextView) findViewById(R.id.txtTime);
        cancleButton = (Button) findViewById(R.id.btnCancel);
        okButton = (Button) findViewById(R.id.btnOK);
        editText = (EditText) findViewById(R.id.edtRemark);

    }

    private void getValueFromIntent() {
        passengerStrings = getIntent().getStringArrayExtra("Passenger");
        myConstant = new MyConstant();
    }

    private void setupFragmentMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        createCenterMap(13.668087, 100.622625);

        createMarker();


    }   // onMapReady

    private void createCenterMap(double lat, double lng) {
        centerLatLng = new LatLng(lat, lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));
    }

    private void createMarker() {

        try {

            mMap.clear();
            startLatLng = new LatLng(startLatADouble, startLngADouble);
            destinationLatLng = new LatLng(destinationLatADouble, destinationLngADouble);

            startMarker = new MarkerOptions()
                    .position(startLatLng)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.ic_marker_start));

            destinationMarker = new MarkerOptions()
                    .position(destinationLatLng)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.ic_marker_destination));

            mMap.addMarker(startMarker);
            mMap.addMarker(destinationMarker);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
        if (view == dateImageView) {
            showDateDialog();
        }
        if (view == timeImageView) {
            showTimeDialog();
        }
        if (view == cancleButton) {
            finish();
        }
        if (view == okButton) {

            remarkString = editText.getText().toString().trim();
            if (remarkString.equals("")) {
                remarkString = " ";
            }
            Log.d("21AprilV3", "Clicl OK");
            uploadJobToServer();
        }


    }   // onClick

    private void uploadJobToServer() {

        String tag = "21AprilV3";
        Log.d(tag, "ID_passenger ==> " + passengerStrings[0]);
        Log.d(tag, "Date ==> " + dateString);
        Log.d(tag, "Time ==> " + timeString);
        Log.d(tag, "NameStart ==> " + startTextView.getText());
        Log.d(tag, "NameLat ==> " + startLatADouble);
        Log.d(tag, "NameLng ==> " + startLngADouble);
        Log.d(tag, "Destination ==> " + destinationTextView.getText());
        Log.d(tag, "DestinationLat ==> " +destinationLatADouble);
        Log.d(tag, "DestinationLng ==> " + destinationLngADouble);

    }   // uploadJob

    private void showTimeDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(MapsActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minus) {
                        showTime(hour, minus, 0);
                    }
                }, hourAnInt, minusAnInt, false);
        timePickerDialog.show();
    }

    private void showDateDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(MapsActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,
                                          int year, int month, int day) {
                        showDate(day, month, year);
                    }
                }, intYear, intMonth, intDay);
        datePickerDialog.show();

    }

    private void getCurrentDataTime() {
        String tag = "21AprilV2";
        Calendar calendar = Calendar.getInstance();
        intDay = calendar.get(Calendar.DAY_OF_MONTH);
        intMonth = calendar.get(Calendar.MONTH);
        intYear = calendar.get(Calendar.YEAR);
        hourAnInt = calendar.get(Calendar.HOUR_OF_DAY);
        minusAnInt = calendar.get(Calendar.MINUTE);
        secondAnInt = calendar.get(Calendar.SECOND);

        Log.d(tag, "Day ==> " + intDay);
        Log.d(tag, "Month ==> " + intMonth);
        Log.d(tag, "Year ==> " + intYear);

        showDate(intDay, intMonth, intYear);
        showTime(hourAnInt, minusAnInt, secondAnInt);

    }

    private void showTime(int hourAnInt, int minusAnInt, int secondAnInt) {
        timeString = Integer.toString(hourAnInt) + ":" +
                Integer.toString(minusAnInt) + ":" +
                "00";
        timeTextView.setText(timeString);
    }

    private void showDate(int intDay, int intMonth, int intYear) {
        dateString = Integer.toString(intDay) + "/" +
                Integer.toString(intMonth + 1) + "/" +
                Integer.toString(intYear);
        dateTextView.setText(dateString + " ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        resultStrings = data.getStringArrayExtra("Result");
        showTextAnMarker(requestCode, resultStrings);


    }   // onActivityResult

    private void showTextAnMarker(int requestCode, String[] resultStrings) {

        Log.d(tag, "requestCode ==> " + requestCode);
        for (int i = 0; i < resultStrings.length; i++) {
            Log.d(tag, "resultString(" + i + ") ==> " + resultStrings[i]);
        }

        switch (requestCode) {
            case 1000:

                startTextView.setText(resultStrings[1]);
                startLatADouble = Double.parseDouble(resultStrings[2]);
                startLngADouble = Double.parseDouble(resultStrings[3]);
                createCenterMap(startLatADouble, startLngADouble);

                break;
            case 1001:

                destinationTextView.setText(resultStrings[1]);
                destinationLatADouble = Double.parseDouble(resultStrings[2]);
                destinationLngADouble = Double.parseDouble(resultStrings[3]);
                createCenterMap(destinationLatADouble, destinationLngADouble);

                break;
        }

        createMarker();

        requestDirection();


    }   // showTextAnMarker

    public void requestDirection() {

        GoogleDirection.withServerKey(myConstant.getServerKeyString())
                .from(startLatLng)
                .to(destinationLatLng)
                .transportMode(TransportMode.DRIVING)
                .execute(this);


    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        String tag = "21AprilV1";

        if (direction.isOK()) {

            ArrayList<LatLng> directionPositionList = direction.getRouteList()
                    .get(0).getLegList().get(0).getDirectionPoint();
            mMap.addPolyline(DirectionConverter
                    .createPolyline(this, directionPositionList, 5, Color.BLUE));

            Route route = direction.getRouteList().get(0);
            Leg leg = route.getLegList().get(0);
            distanceString = leg.getDistance().getText();
            Log.d(tag, "distance ==> " + distanceString);

            distanceTextView.setText(distanceString);
            priceTextView.setText(calculatePrice(distanceString));

        }   // if

    }

    private String calculatePrice(String distanceString) {

        String[] strings = distanceString.split("km");
        String tag = "21AprilV1";
        Log.d(tag, "strings(0) ==> " + strings[0]);

        double douPrice = Double.parseDouble(strings[0]) * 2;


        return Double.toString(douPrice);
    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }
}   // Main Class
