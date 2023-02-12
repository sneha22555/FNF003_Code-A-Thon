package com.example.mysafety;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mysafety.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int LOCATION_PERMISSION_CODE = 101;
    FloatingActionButton addinglocation;
    List<Address> listGeoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(isLocationisGranted()){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            try {
                listGeoCoder = new Geocoder(this).getFromLocationName("Marine Lines, Mumbai, Maharashtra", 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            double longitude = listGeoCoder.get(0).getLongitude();
            double latitude = listGeoCoder.get(0).getLatitude();
            Log.i("Google_Map_Tag", "Address has Longitude :::" + String.valueOf(longitude) + "Address has latitude" + String.valueOf(latitude));
        }
        else{
            requestLocationPermission();
        }

        addinglocation = findViewById(R.id.add_location_btn);

        addinglocation.setOnClickListener((v)-> startActivity(new Intent(MapsActivity.this,reportLocation.class)));


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mumbai = new LatLng(19.067921100980115, 72.90314795781727);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Marker in Mumbai"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {

            mMap.setMyLocationEnabled(true);
        }
    }

    private boolean isLocationisGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            return false;
        } else {
            return true;
        }

    }

    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
    }

}