package com.lucian.servicestackoverflow42126979;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public final class WebSocketNotification extends WebSocketListener {

    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0,  TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://192.168.0.5:3000/cable")
                .build();

        client.newWebSocket(request, this);

        client.dispatcher().executorService().shutdown();
    }

    @Override public void onOpen(WebSocket webSocket, Response response) {
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        String json = "";

        try {
            obj1.put("command", "subscribe");
//            obj2.put("channel", "user_id_321");
            obj1.put("identifier", "{\"channel\": \"ChatChannel\", \"user_id\": 2}");
            json = obj1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        webSocket.send(json);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        try {
            JSONObject data = new JSONObject(text);

            if (!data.has("type")) {
                Log.i("Websocket", data.toString());

                JSONObject message = new JSONObject(data.getString("message"));
                JSONObject record = new JSONObject(message.getString("message_record"));

                NotificationService.push(record.getString("origin_user_id"), record.getString("content"));
            }
        } catch (JSONException e) {
            System.out.println("\n\n======================================\n\n");
            e.printStackTrace();
        }
    }



    /* magic methods */

//    @Override
//    public void onClosing(WebSocket webSocket, int code, String reason) {
//        webSocket.close(1000, null);
//        System.out.println("CLOSE: " + code + " " + reason);
//
//        MainActivity.getAndStartNotificationService(true);
//    }

//    @Override
//    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
//        MainActivity.getAndStartNotificationService(true);
//    }

//    @Override
//    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
//        MainActivity.getAndStartNotificationService(true);
//    }

}

