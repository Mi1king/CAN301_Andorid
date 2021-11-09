package com.example.mmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Lab9Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            int current = intent.getExtras().getInt("level");
            int max = intent.getExtras().getInt("scale");
            int value = current * 100 / max;
            Toast.makeText(context, "Battery Level is:" + value + "%", Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "Boot Completed", Toast.LENGTH_LONG).show();
        }

        //to receive my own action
        if (intent.getAction().equals("MyCustomAction")) {
            String data = intent.getExtras().getString("data");
            Toast.makeText(context, "MyAction received" + data, Toast.LENGTH_LONG).show();
        }
    }

}