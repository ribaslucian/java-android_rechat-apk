package com.lucian.servicestackoverflow42126979;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationService extends Service {

    private static String CHANNEL_ID = "WhatsAppResearcher";
    private static String CHANNEL_NAME = "WhatsApp Researcher Notifications";
    private static String CHANNEL_DESCRIPTION = "WhatsApp Researcher Notifications";

    private static PendingIntent pendingIntent;
    private static Context contextForNotification;
    private static Context contextForIntent;

    public static Context context;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
//        super.onCreate();

        createNotificationChannel();
        contextForNotification = getApplicationContext();
        contextForIntent = this;

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(contextForIntent, 0, intent, 0);

        context = this;

//        push("Notification", "Serviço iniciado");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void push(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(contextForIntent, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_whatsapp_researcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(contextForNotification);
        notificationManager.notify(1, builder.build());
    }

}