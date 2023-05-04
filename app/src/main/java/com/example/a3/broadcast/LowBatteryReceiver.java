package com.example.a3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a3.CharacterInfo;

public class LowBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // its simple but it does everything we need it to
        if (!CharacterInfo.CurrentCharacter.isSaved) {
            Toast.makeText(context, "Low Battery: please store any unsaved data", Toast.LENGTH_LONG).show();
        }
    }
}
