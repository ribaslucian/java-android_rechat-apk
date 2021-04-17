package com.lucian.utfpr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());


//        _Session s = new _Session(context);
//        String title = s.getusename();.

//        try {
//            SharedPreferences sharedPreferences = context.getSharedPreferences("utfpr",  Context.MODE_PRIVATE);
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



//        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String user_id = prefs.getString("user_id", "");
//        NotificationHelper.push(context, "Rechat", user_id);













//        NotificationHelper.push(context, "Rechat", "Você pode ter novas mensagens.");




//        try {
//            URL url = new URL("http://192.168.0.5:3000/api/get_notification/2");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String content = "", line;
//
//            while ((line = rd.readLine()) != null)
//                content += line;
//
//            JSONObject obj = new JSONObject(content);
//            Log.d("__GET__", obj.toString());
//
//            Boolean notification = (Boolean) obj.get("notification");
//            if (notification) {
//                NotificationHelper.push(context, (String) obj.get("contact"), (String) obj.get("content"));
//            } else {
//                NotificationHelper.push(context, "ReChat", "Você pode ter novas mensagens.");
//            }
//
////            NotificationHelper.push(this, (String) obj.get("title"), (String) obj.get("content"));
//        } catch (Exception e) {
//            Log.d("__GET", e.toString());
//            e.printStackTrace();
//            NotificationHelper.push(context, "ReChat(!)", "Você pode ter novas mensagens.");
//        }



        NotificationThread n = new NotificationThread(context);
        n.start();




    }

}