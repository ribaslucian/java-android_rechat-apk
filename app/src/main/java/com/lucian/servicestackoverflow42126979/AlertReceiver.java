package com.lucian.servicestackoverflow42126979;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());

        NotificationHelper.push(context, "Rechat", "Você pode ter novas mensagens.");
    }

}