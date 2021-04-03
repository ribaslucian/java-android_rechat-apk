package com.lucian.servicestackoverflow42126979;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());


//        _Session s = new _Session(context);
//        String title = s.getusename();.

//        try {
//            SharedPreferences sharedPreferences = context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
//            String result = sharedPreferences.getString("user_id","");
//            NotificationHelper.push(context, result, "Você pode ter novas mensagens.");
//
//            String user_id = MainActivity.preferences.getString("user_id", "");
//            NotificationHelper.push(context, user_id, "Você pode ter novas mensagens.");
//
//        } catch (Exception e) {
//
//            NotificationHelper.push(context, "Error", e.getMessage());
//        }



        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String user_id = prefs.getString("user_id", "");
        NotificationHelper.push(context, "Rechat", user_id);













//        NotificationHelper.push(context, "Rechat", "Você pode ter novas mensagens.");
    }

}