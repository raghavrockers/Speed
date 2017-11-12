package com.example.shreyas.speed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shreyas on 11/11/2017.
 */

public class ZoneActivity extends FragmentActivity implements OnMapReadyCallback {

    Double latitude = 22.08;
    Double longitude = 72.07;
    int zone_points_number = 0;

    int i = 0;
    private GoogleMap mMap;
    Double[] array = new Double[8];

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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).title("New Marker");
                mMap.addMarker(marker);

                array[i++] = point.latitude;
                array[i++] = point.longitude;

                //Intent intent = new Intent(ZoneActivity.this,ConformActivity.class);
                //intent.put

                //ZonepointsActivity zonalPoints = new ZonepointsActivity(point.latitude,point.longitude);
                //mDatabase.child("Zones in India").setValue(zonalPoints);




                if(zone_points_number == 3) {
                    Intent intent = new Intent();

                    intent.putExtra("keyName", array[0] + " " + array[1] + " " +
                                    array[2] + " " + array[3] + " " + array[4] + " " + array[5] + " " +
                                    array[6] + " " + array[7] );
                    setResult(RESULT_OK, intent);
                    finish();
                   // startActivity(new Intent(ZoneActivity.this,ConformActivity.class));
                }
                zone_points_number ++;

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
