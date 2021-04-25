package com.example.shugan.myapplicationalarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;

import static android.content.Context.BIND_AUTO_CREATE;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    RecyclerView.ViewHolder viewHolder;


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context.getApplicationContext(), MyService.class);
        context.getApplicationContext().startService(service);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mp = MediaPlayer.create(context, uri);
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
        //Toast.makeText(context, "id"+ viewHolder.itemView.getTag(), Toast.LENGTH_SHORT).show();


    }

}