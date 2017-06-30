package nakthon.soraya.calldriver;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nakthon.soraya.calldriver.fragment.ShowDetailOrderFragment;

public class ShowDetailOrderActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_order);

        //Fragment Map
        fragmentMap();

        //Fragmetn Show Text
        fragmetnShowText(savedInstanceState);

    }   // Main Method

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMapReady

}   // Main Class
