package com.example.android1to3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action=intent.getAction();
        Log.i("ACTION", action);
        Toast.makeText(context, "Airoplane Mode Change", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String smsSender="", smsBody="";
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsSender = smsMessage.getDisplayOriginatingAddress();
                    smsBody += smsMessage.getMessageBody();
                }
                Log.i("SENDER", smsSender);
                Log.i("Body", smsBody.substring(smsBody.length()-6));

            }
        }
    }

    public interface DoctorInterface{
        void OnDoctorCallBack();
    }


}
