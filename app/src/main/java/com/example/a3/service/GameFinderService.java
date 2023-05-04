/*
File: MainActivity.java
Project: MAD-A3
Programmer: Isaiah Bartlett
Date: 4/10/2023
Description: the main activity that holds all of the other fragments
 */


package com.example.a3.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.a3.LocalGame;
import com.example.a3.MainActivity;
import com.example.a3.R;
import com.example.a3.functions.mapFunctions;

import java.util.Timer;
import java.util.TimerTask;

public class GameFinderService extends Service {
    Timer timer;

    public GameFinderService() {
    }

    @Override
    public void onCreate() {
        // lets pretend we are pulling down locations from a remote database and adding them to our local map
        for (int i = 0; i < 5; i++) {
            LocalGame.serverList.add(new LocalGame(mapFunctions.getRandomMarkerName(), mapFunctions.getRandomPoint(LocalGame.currentLocation)));
        }
        LocalGame.sync();

        // run background task that will periodically look for updates in said imaginary database
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LocalGame game = new LocalGame(mapFunctions.getRandomMarkerName(), mapFunctions.getRandomPoint(LocalGame.currentLocation));
                LocalGame.serverList.add(game);

                // notification
                createNotification(game);

                // broadcast
                Intent intent = new Intent();
                intent.setAction(getPackageName() + ".GAME_BROADCAST");
                intent.putExtra("title", game.getGameTitle());
                intent.putExtra("lat", game.getGameLocation().latitude);
                intent.putExtra("lng", game.getGameLocation().longitude);
                sendBroadcast(intent);
            }
        };

        timer = new Timer(true);
        timer.schedule(task, 10000, 60000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // this is unbound
    }


    void createNotification(LocalGame game) {
        Intent notificationIntent = new Intent(this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra("findGame", true);

        int piFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, piFlag);

        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this, getResources().getString(R.string.notify_channel))
                .setSmallIcon(R.drawable.ic_menu_map)
                .setTicker(getResources().getString(R.string.notify_title))
                .setContentTitle(getResources().getString(R.string.notify_title))
                .setContentText(game.getGameTitle())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Android 8.0 stuff. Probably a good idea to use with the latest SDK
            CharSequence name = getResources().getString(R.string.notify_name);
            String description = getResources().getString(R.string.notify_desc);
            NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.notify_channel), name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            // Register the channel with the system
            manager.createNotificationChannel(channel);
            if (manager.areNotificationsEnabled()) {
                manager.notify(1,myBuilder.build());
            }
        }
    }
}