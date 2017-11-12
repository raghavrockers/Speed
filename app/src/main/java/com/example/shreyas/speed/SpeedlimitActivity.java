package com.example.shreyas.speed;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/3/2017.
 */

public class SpeedlimitActivity extends AppCompatActivity {
    @BindView(R.id.speed_limit_value)
    EditText speed_limit_value;

    @BindView(R.id.constraint_layout)
    ConstraintLayout constraintLayout;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speedlimit);
        ButterKnife.bind(this);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
    }
    @OnClick(R.id.go)
    public void click(){
        String speed_limit = speed_limit_value.getText().toString().trim();
        if(TextUtils.isEmpty(speed_limit)) {
            Toast.makeText(getApplicationContext(), "Enter Speed Limit!", Toast.LENGTH_SHORT).show();
            return ;
        }

        else{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:7597526581"));
            intent.putExtra("sms_body", "#" +  speed_limit);
            if (intent.resolveActivity(getPackageManager()) != null) {
                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            //startActivity(new Intent(this,MainActivity.class));
            //finish();
        }
        speed_limit_value.setText("");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }

}
