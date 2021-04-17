package com.lucian.servicestackoverflow42126979;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "RechatChannelID";
    public static final String channelName = "RechatChannelName";

    private NotificationManager mManager;


    private static PendingIntent pendingIntent;
    private static Context contextForNotification;
    private static Context contextForIntent;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }

        contextForNotification = getApplicationContext();
        contextForIntent = this;

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(contextForIntent, 0, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String content) {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_whatsapp_researcher);
    }

    public static void push(Context context, String title, String content) {
//        if (MainActivity.isAppRunning()) {
//            return;
//        }

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(title, content);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(content);
        nb.setStyle(bigTextStyle);

//        final int min = 1;
//        final int max = 1000;
//        final int random = new Random().nextInt((max - min) + 1) + min;
        notificationHelper.getManager().notify(1, nb.build());
    }
}
