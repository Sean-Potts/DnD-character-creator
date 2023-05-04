package com.example.a3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.a3.service.GameFinderService;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, GameFinderService.class);
        context.startService(service);
        Log.d("debug receivers", "onReceive: ");
    }
}