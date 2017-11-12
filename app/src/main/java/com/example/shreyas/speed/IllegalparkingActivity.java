package com.example.shreyas.speed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shreyas on 11/3/2017.
 */

public class IllegalparkingActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Registered cars");

    @BindView(R.id.car_number)
    EditText car_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegalparking);
        ButterKnife.bind(this);
    }

    

    @OnClick(R.id.go)
    public void submit(){
        String car_plate_number = car_number.getText().toString().trim();
        if(TextUtils.isEmpty(car_plate_number)) {
            Toast.makeText(getApplicationContext(), "Enter Speed Limit!", Toast.LENGTH_SHORT).show();
            return ;
        }

        else{

            mDatabase.child(car_plate_number).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    UserinfoActivity userInfo = dataSnapshot.getValue(UserinfoActivity.class);
                    String person_name  = userInfo.getUserName().trim();
                    String person_number = userInfo.getUserNumber().trim();

                    // code to send message

                    Toast.makeText(getApplicationContext(), person_name + " " + person_number , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"+ person_number));
                    intent.putExtra("sms_body",person_name + "\n" + "\n" + "Please move your car within 5 minutes its causing a traffic jam or you will be fined");
                    if (intent.resolveActivity(getPackageManager()) != null) {startActivity(intent);}
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), " Error please try again ", Toast.LENGTH_SHORT).show();
                }
            });
        }

        car_number.setText("");
    }

}
