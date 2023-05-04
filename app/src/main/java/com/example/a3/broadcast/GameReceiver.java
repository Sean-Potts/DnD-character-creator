package com.example.a3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.a3.LocalGame;
import com.google.android.gms.maps.model.LatLng;

public class GameReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        double lat = intent.getDoubleExtra("lat", 0);
        double lng = intent.getDoubleExtra("lng", 0);

        Log.d("game receivers", "received: " + title);
        LocalGame.add(title, new LatLng(lat, lng));
    }
}
