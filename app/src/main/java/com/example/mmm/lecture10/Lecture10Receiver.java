package com.example.mmm.lecture10;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lecture10Receiver extends BroadcastReceiver {

    String phoneNumber = "null";
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        SharedPreferences prefs = context.getSharedPreferences("Phone", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int num = prefs.getInt("new_number",0) + 1;
        editor.clear();
//        editor.putString("new_number", phoneNumber);
        editor.putInt("new_number", num);
        editor.commit();
    }
}