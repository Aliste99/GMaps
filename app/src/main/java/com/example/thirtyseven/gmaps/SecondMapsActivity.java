package com.example.thirtyseven.gmaps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SecondMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng bishkek = new LatLng(42.85119290572019, 74.60303650000003);
        //mMap.addMarker(new MarkerOptions().position(bishkek).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bishkek, 15));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent myIntent = new Intent(SecondMapsActivity.this, AddFlatActivity.class);
                lat = latLng.latitude;
                lng = latLng.longitude;
                myIntent.putExtra("newlat", lat);
                myIntent.putExtra("newlng", lng);
                finish();
                startActivity(myIntent);


            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null) {
                    marker.remove();
                    marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Ваш маркер"));
                }else {
                    marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Ваш маркер"));
                    lat = latLng.latitude;
                    lng = latLng.longitude;
                }
            }
        });



    }
}
