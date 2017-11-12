package com.example.shreyas.speed;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
    private static final int ACCESS_FINE_LOCATION_PERMISSIONS_RESULT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToAccessLocation();
        }

    }


    private void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_SMS)) {
                    Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_SMS},
                        READ_SMS_PERMISSIONS_REQUEST);
            }
        }
    }



    private void getPermissionToAccessLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_FINE_LOCATION_PERMISSIONS_RESULT);
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST || requestCode == ACCESS_FINE_LOCATION_PERMISSIONS_RESULT ) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    @OnClick(R.id.btn_set_speed_limit)
    public void submitOne(){
        startActivity(new Intent(MainActivity.this,SpeedlimitActivity.class));
    }

    @OnClick(R.id.btn_report_illegal_parking)
    public void submitTwo(){
        startActivity(new Intent(MainActivity.this,IllegalparkingActivity.class));
    }

    @OnClick(R.id.btn_register_car)
    public void submitThree(){
        startActivity(new Intent(MainActivity.this,RegistercarActivity.class));
    }

    @OnClick(R.id.btn_track_my_car)
    public void submitFour(){
        startActivity(new Intent(MainActivity.this,TrackActivity.class));
    }

    @OnClick(R.id.btn_report_accident)
    public void submitFive(){
        startActivity(new Intent(MainActivity.this,ReportaccidentActivity.class));
    }

    @OnClick(R.id.btn_set_zone)
    public void submitSix(){
        startActivity(new Intent(MainActivity.this,ZonenameActivity.class));
    }

    @OnClick(R.id.btn_zonal_tracking)
    public void submitSeven(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:8107427069"));
        intent.putExtra("sms_body", "#G");
        if (intent.resolveActivity(getPackageManager()) != null) {
            Toast.makeText(getApplicationContext(), "Zonal tracking set!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

}
