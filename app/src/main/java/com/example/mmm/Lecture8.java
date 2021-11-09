package com.example.mmm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Lecture8 extends AppCompatActivity {

    final String[] CHANNEL_IDS = {"CH1", "CH2", "CH3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture8);

        // Toast
        Toast toast = Toast.makeText(this, "Toasted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_HORIZONTAL, 110, 110);
        toast.show();

        createNotificationChannel();
        Button notif = findViewById(R.id.lecture8_notfi_button);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Notification.Builder builder =
                            new Notification.Builder(Lecture8.this, CHANNEL_IDS[0]);
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setContentTitle("Notification title");
                    builder.setContentText("My app's notification content");
                    Intent redir = new Intent(Lecture8.this, Calculator.class);
                    PendingIntent pendingIntent =
                            PendingIntent.getActivity(Lecture8.this, 0, redir, 0);
                    builder.setContentIntent(pendingIntent);


                    Notification notification = builder.build();
                    NotificationManager manager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(1, notification);
                    // after a long time
                    manager.cancel(1);

                }
            }
        });


        Intent intent = new Intent(this, Lecture8Service.class);
        startService(intent);
//        stopService(intent);


        //play music service
        Button playButton = findViewById(R.id.lecture8_play_button);
        playButton.setText("Play music");
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService == null){
                    Intent intent = new Intent(Lecture8.this, Lecture8Service.class);
                    bindService(intent, mConnection, BIND_AUTO_CREATE);
                }else{
                    myService.play();
                }

            }
        });


        Button lecture8_pause_button = findViewById(R.id.lecture8_pause_button);
        lecture8_pause_button.setText("Pause");
        lecture8_pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Lecture8.this, Lecture8Service.class);
//                bindService(intent, mConnection, BIND_AUTO_CREATE);
                myService.pause();
            }
        });


        Button lecture8_stop_button = findViewById(R.id.lecture8_stop_button);
        lecture8_stop_button.setText("Stop");
        lecture8_stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Lecture8.this, Lecture8Service.class);
//                bindService(intent, mConnection, BIND_AUTO_CREATE);
                myService.stop();
            }
        });
    }

    Lecture8Service myService;
    boolean myServiceConnected;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected
                (ComponentName componentName, IBinder iBinder) {
            Lecture8Service.MyBinder myBinder = (Lecture8Service.MyBinder) iBinder;
            myService = myBinder.getService();
            myServiceConnected = true;
            myService.play();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
            myServiceConnected = false;
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        if (myServiceConnected) {
            unbindService(mConnection);
            myServiceConnected = false;
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_IDS[0],
                    "Channel 1 (channel name)",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("description 1");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_IDS[1],
                    "Channel 2 (channel name)",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel2.setDescription("description 2");

            NotificationChannel channel3 = new NotificationChannel(CHANNEL_IDS[2],
                    "Channel 2 (channel name)",
                    NotificationManager.IMPORTANCE_MIN);
            channel3.setDescription("description 3");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
            notificationManager.createNotificationChannel(channel3);
        }
    }


}