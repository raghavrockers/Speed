package com.example.shreyas.speed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shreyas on 11/8/2017.
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if(bundle!= null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            String senderNumber = null;
            for (int i = 0 ; i < pdus.length ; i++ ){
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String message = sms.getDisplayMessageBody();
                //Toast.makeText(context,message,Toast.LENGTH_SHORT).show();


                String[] array = message.split(",");


                TrackmycarActivity inst = TrackmycarActivity.instance();
                inst.location(array[0],array[1]);
            }
        }
    }
}
