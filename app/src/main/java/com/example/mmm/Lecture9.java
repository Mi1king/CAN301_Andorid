package com.example.mmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Lecture9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture9);

        Button calcButton = (Button) findViewById(R.id.lecuter9_button1);
        calcButton.setText("calcButton");
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCalculator();
            }
        });


        Button broButton = (Button) findViewById(R.id.lecuter9_button2);
        broButton.setText("broButton");
        broButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentBrowser();

            }
        });


        //monitoring
        ToggleButton monitorBtn = findViewById(R.id.Lecture9monitorBtn);
        final TextView batteryLvl = findViewById(R.id.Lecture9BatteryLevelText);
        final BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(intent.ACTION_BATTERY_CHANGED)) {
                    int current = intent.getExtras().getInt("level");
                    int max = intent.getExtras().getInt("scale");
                    int value = current * 100 / max;
                    batteryLvl.setText("Battery Level is:" + value + "%");
                }
            }
        };


        monitorBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                    registerReceiver(receiver, filter);
                } else {
                    unregisterReceiver(receiver);
                    batteryLvl.setText("Back to Zzz");
                }
            }
        });

        //lab 9
//        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter filter = new IntentFilter("MyCustomAction");
        registerReceiver(new Lab9Receiver(), filter);


        // button to send a broadcast with my own action type "MyCustomAction"
        Button lectuer9_sendB_button = (Button) findViewById(R.id.lectuer9_sendB_button);
        lectuer9_sendB_button.setText("Send Broadcast");
        lectuer9_sendB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("MyCustomAction");
                intent.putExtra("data", "Nothing to see here, move along.");
                sendBroadcast(intent);
            }
        });
    }

    private void intentBrowser() {
        Uri webpage = Uri.parse("https://www.xjtlu.edu.cn");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void intentCalculator() {
        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_MAIN);
        intent.setAction("CALCULATOR");
        intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}