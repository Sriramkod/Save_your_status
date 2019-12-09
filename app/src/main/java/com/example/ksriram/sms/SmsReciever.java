package com.example.ksriram.sms;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SmsReciever extends BroadcastReceiver {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null){

            Object[] pdus = (Object[]) bundle.get("pdus");
            String senderNumber=null;
            String message = null;
            for (int i = 0; i <pdus.length ; i++) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[i]);
                 senderNumber = sms.getOriginatingAddress();

                 message = sms.getDisplayMessageBody();
                Toast.makeText(context,senderNumber+" "+message,Toast.LENGTH_SHORT).show();

            }

           SmsManager smsManager = SmsManager.getDefault();

           try {
               Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
               intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
               intentone.putExtra("message",message);
               intentone.putExtra("sender",senderNumber);
               context.startActivity(intentone);
           }
           catch (Exception e){
           }
        }

    }
}
