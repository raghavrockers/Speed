package com.example.shreyas.speed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/8/2017.
 */

public class TrackmycarActivity extends FragmentActivity implements OnMapReadyCallback {

    private static TrackmycarActivity inst;

    Double latitude = 22.08;
    Double longitude = 72.07;

    // latitude = 26.9124
    // longitude = 75.7873

    private GoogleMap mMap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng currentPosition = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currentPosition).title("Your Car's Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");

                mMap.addMarker(marker);

                Toast.makeText(getApplicationContext(), point.latitude+ " " + point.longitude, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    public static TrackmycarActivity instance(){
        return inst;
    }

    public void location(String lat , String lon){
        latitude = Double.parseDouble(lat);
        longitude = Double.parseDouble(lon);
        LatLng currentPosition = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currentPosition).title("Your Car's Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }


}
