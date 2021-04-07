package com.lucian.servicestackoverflow42126979;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import androidx.preference.PreferenceManager;
import android.se.omapi.Session;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    private static MainActivity self = null;
    private static NotificationService notificationService = null;
    private static WebSocketNotification webSocket = null;
    private WebView webView;
    public static Boolean alarmStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MainActivity.context = getApplicationContext();
        MainActivity.context = this;





        webView = findViewById(R.id.webview);
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);

//         webView.loadUrl("http://54.207.155.116");
//         webView.loadUrl("http://ec2-18-228-83-110.sa-east-1.compute.amazonaws.com/");
//        webView.loadUrl("http://10.0.2.2:3000");
        webView.loadUrl("http://200.134.10.26");























//        https://pt.stackoverflow.com/questions/69894/como-resolver-o-erro-networkonmainthreadexception

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("user_id", "__user_id");
//        editor.commit();
//        editor.apply();



//        preferences = getSharedPreferences("rechat", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("user_id", "2");
//        editor.commit();
//        editor.apply();



//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);





//        try {
//            URL url = new URL("http://192.168.0.5:3000/api/get_notification/2");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setDoOutput(true);
////            connection.setConnectTimeout(5000);
////            connection.setReadTimeout(5000);
//            connection.connect();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////            String content = "", line;
////
////            while ((line = rd.readLine()) != null) {
////                content += line + "\n";
////            }
////
////            Log.d("__GET__", content);
//
//            connection.disconnect();
//        } catch (Exception e) {
//            Log.d("__GET_ERROR", e.getMessage());
//            e.printStackTrace();
//        }




//        _Session s = new _Session(this);
//        s.setusename("teste123");

//        Log.d("__GET__", s.getusename());











//        try {
//
//            URL url = new URL("http://192.168.0.5:3000/api/get_notification/2");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
////            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
////            readStream(in);
//
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//
//            String content = "", line;
//
//            while ((line = rd.readLine()) != null) {
//                content += line;
//            }
////            Log.d("__GET__", content);
//
//            JSONObject obj = new JSONObject(content);
//            Log.d("__GET__", obj.toString());
//
//
////            NotificationHelper.push(this, (String) obj.get("title"), (String) obj.get("content"));
//        } catch (NetworkOnMainThreadException | MalformedURLException e) {
//            Log.d("__GET", e.toString());
////            e.printStackTrace();
//        } catch (IOException e) {
//            Log.d("__GET", e.toString());
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        NotificationHelper.push(this,"Rechat", "Chefe: Menina de 13 anos morre na Alemanha por usar máscara e aspirar CO2.");

//        NotificationService.push("Oi", "oi");

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.setWebViewClient(new WebViewClient(){

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        startAlarms(MainActivity.context);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public static void startAlarms(Context context) {
        if (alarmStarted) {
            return;
        }

        //Definir início para as 10 horas
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 7);
//        calendar.set(Calendar.MINUTE, 47);
//        calendar.set(Calendar.SECOND, 0);

//        Calendar calendar = Calendar.getInstance().getTimeInMillis();

        // definir intervalo de 8 horas  em milissegundos
        // long intervalo = 8*60*60*1000;

        long intervalo = 10*1000;

        Intent tarefaIntent = new Intent(context, AlertReceiver.class);
        PendingIntent tarefaPendingIntent = PendingIntent.getBroadcast(context,4321, tarefaIntent,0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Definir o alarme para acontecer de 6 em 6 horas a partir das 10 horas
        // RTC_WAKEUP
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), intervalo, tarefaPendingIntent);

        alarmStarted = true;
    }

    public static Context context = null;

    public static boolean isAppRunning() {
        if (MainActivity.context == null) {
            return false;
        }

        String packageName = "com.lucian.servicestackoverflow42126979";
        Context context = MainActivity.context;


        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String  performPostCall(String requestURL,
                                   HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

