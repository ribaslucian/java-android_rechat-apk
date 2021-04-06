package com.lucian.servicestackoverflow42126979;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationThread extends Thread {

    Context context;

    NotificationThread(Context c) {
        context = c;
    }

    public void run() {

        String default_message = "Você tem novas mensagens, quer dar uma olhada?";



        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id","0");

//        Log.d("__GET__", user_id);

        if (user_id.equals("0")) {
            NotificationHelper.push(context, "ReChat", default_message);
            return;
        }

        try {
            URL url = new URL("http://200.134.10.26/api/get_notification/" + user_id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String content = "", line;

            while ((line = rd.readLine()) != null)
                content += line;

            JSONObject obj = new JSONObject(content);
            Log.d("__GET__", obj.toString());

            Boolean notification = (Boolean) obj.get("notification");
            if (notification) {
                NotificationHelper.push(context, (String) obj.get("contact"), (String) obj.get("content"));
            } else {
                NotificationHelper.push(context, "ReChat", default_message);
            }

//            NotificationHelper.push(this, (String) obj.get("title"), (String) obj.get("content"));
        } catch (Exception e) {
//            Log.d("__GET", e.toString());
//            e.printStackTrace();
            NotificationHelper.push(context, "ReChat", default_message);
        }

    }
}