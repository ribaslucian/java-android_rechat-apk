package com.lucian.servicestackoverflow42126979;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MainActivity self = null;
    private static NotificationService notificationService = null;
    private static WebSocketNotification webSocket = null;
    private WebView webView;
    public static Boolean alarmStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();

        webView = findViewById(R.id.webview);

        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
//         webView.loadUrl("http://54.207.155.116");
         webView.loadUrl("http://ec2-18-228-83-110.sa-east-1.compute.amazonaws.com/");
//        webView.loadUrl("http://10.0.2.2:3000");
        // NotificationHelper.push(this,"Rechat", "Chefe: Menina de 13 anos morre na Alemanha por usar máscara e aspirar CO2.");
        // NotificationService.push("Oi", "oi");

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

        startAlarms(this);
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        //Definir intervalo de 6 horas
        long intervalo = 8*60*60*1000; // 3 horas em milissegundos
//        long intervalo = 10*1000;

        Intent tarefaIntent = new Intent(context, AlertReceiver.class);
        PendingIntent tarefaPendingIntent = PendingIntent.getBroadcast(context,4321, tarefaIntent,0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Definir o alarme para acontecer de 6 em 6 horas a partir das 10 horas
        // RTC_WAKEUP
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalo, tarefaPendingIntent);

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
}

