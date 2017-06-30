package nakthon.soraya.calldriver;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import nakthon.soraya.calldriver.fragment.ShowDetailOrderFragment;

public class ShowDetailOrderActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String idString, latStartString, lngStartString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_order);

        //Get Value From Intent
        getValueFromIntent();

        //Fragment Map
        fragmentMap();

        //Fragmetn Show Text
        fragmetnShowText(savedInstanceState);

    }   // Main Method

    private void getValueFromIntent() {
        idString = getIntent().getStringExtra("idUser");
    }

    private void fragmetnShowText(Bundle savedInstanceState) {

        //Get Intent From MainActivity
        String strID = getIntent().getStringExtra("idUser");
        Log.d("30JuneV2", "id Receive From Intent ==> " + strID);

        if (savedInstanceState == null) {

            ShowDetailOrderFragment showDetailOrderFragment = new ShowDetailOrderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fraContent, showDetailOrderFragment.newInstance(strID))
                    .commit();

        }
    }

    private void fragmentMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        MyConstant myConstant = new MyConstant();
        String tag = "30JuneV3";

        try {

            GetDataWhere getDataWhere = new GetDataWhere(ShowDetailOrderActivity.this);
            getDataWhere.execute("id", idString, myConstant.getUrlGetPureJobWhereID());
            String strJSON = getDataWhere.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            latStartString = jsonObject.getString("LatStart");
            lngStartString = jsonObject.getString("LngStart");

            LatLng latLng = new LatLng(Double.parseDouble(latStartString),
                    Double.parseDouble(lngStartString));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

            createMarker(latLng, R.mipmap.ic_marker_start);

        } catch (Exception e) {
            Log.d(tag, "e onMapReady ==> " + e.toString());
        }



    }   // onMapReady

    private void createMarker(LatLng latLng, int intMarker) {

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(intMarker));
        mMap.addMarker(markerOptions);

    }

}   // Main Class
