package com.example.shreyas.speed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/8/2017.
 */

public class ReportaccidentActivity extends AppCompatActivity {
    @BindView(R.id.accident_location)
    EditText accident_location;

    @BindView(R.id.accident_scale)
    EditText accident_scale;

    @BindView(R.id.other_details)
    EditText other_details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportaccident);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.go)
    public void submit(){
        String location = accident_location.getText().toString().trim();
        if(location.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter location", Toast.LENGTH_SHORT).show();
            return ;
        }

        String scale = accident_scale.getText().toString().trim();
        if(scale.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a scale", Toast.LENGTH_SHORT).show();
            return ;
        }

        String others = other_details.getText().toString().trim();
        if(others.isEmpty())
            others = "None";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:8107427069"));
        intent.putExtra("sms_body", "Accident location = " +  location + "\n" + "Accident scale = " + scale + "\n" + "Other Accident Details = " + others);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Toast.makeText(getApplicationContext(), "Reported accident successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        accident_location.setText("");
        accident_scale.setText("");
        other_details.setText("");
    }

    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    */
}
