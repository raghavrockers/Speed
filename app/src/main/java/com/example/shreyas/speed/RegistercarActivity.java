package com.example.shreyas.speed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/3/2017.
 */

public class RegistercarActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @BindView(R.id.car_number)
    EditText current_car_number;

    @BindView(R.id.current_car_driver)
    EditText current_car_driver;

    @BindView(R.id.current_car_driver_number)
    EditText current_car_driver_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registercar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.go)
    public void submit(){
        String car_number = current_car_number.getText().toString().trim();
        String car_driver = current_car_driver.getText().toString().trim();
        String car_driver_number = current_car_driver_number.getText().toString().trim();
        UserinfoActivity newUser = new UserinfoActivity(car_driver,car_driver_number);
        mDatabase.child("Registered cars").child(car_number).setValue(newUser);

        // clear inputs
        current_car_number.setText("");
        current_car_driver.setText("");
        current_car_driver_number.setText("");
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
