package com.example.shreyas.speed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/9/2017.
 */

public class TrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.send)
    public void sendMessage() {


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:7597526581"));
        intent.putExtra("sms_body","#L");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.go)
    public void goon(){
        startActivity(new Intent(this,TrackmycarActivity.class));
    }

}
