package com.example.mmm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import java.io.File;

public class Lecture8Service extends Service {
    public Lecture8Service() {
    }

    class MyBinder extends Binder {
        public Lecture8Service getService() {
            return Lecture8Service.this;
        }
    }

    private IBinder binder = new MyBinder();
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        player = MediaPlayer.create(this, R.raw.mymusic);
        return binder;
    }

    public void play() {
        if (!isPlaying())
            player.start();
    }


    public void pause(){
        if (isPlaying())
            player.pause();
    }


    public void stop(){
        if (isPlaying())
            player.stop();
    }

    public boolean isPlaying() {
        if (player != null) {
            return player.isPlaying();
        } else {
            return false;
        }
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}