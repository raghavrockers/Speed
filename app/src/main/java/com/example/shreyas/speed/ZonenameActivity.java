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
 * Created by Shreyas on 11/11/2017.
 */

public class ZonenameActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;



    @BindView(R.id.zone_name)
    EditText zonalName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonename);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.go)
    public void submit(){
        Intent intent = new Intent(ZonenameActivity.this,ZoneActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if(resultCode ==  RESULT_OK){
                String returnString = data.getStringExtra("keyName");
                String[] array = returnString.split(" ");
                String name = zonalName.getText().toString().trim();
                ZonepointsActivity abc = new ZonepointsActivity(array[0],array[1]);
                mDatabase.child("Zones in India").child(name).child("Coordinate one").setValue(abc);
                abc = new ZonepointsActivity(array[2],array[3]);
                mDatabase.child("Zones in India").child(name).child("Coordinate two").setValue(abc);
                abc = new ZonepointsActivity(array[4],array[5]);
                mDatabase.child("Zones in India").child(name).child("Coordinate three").setValue(abc);
                abc = new ZonepointsActivity(array[6],array[7]);
                mDatabase.child("Zones in India").child(name).child("Coordinate four").setValue(abc);
            }
        }
    }
}
