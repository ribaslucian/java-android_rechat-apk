package com.lucian.utfpr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //Create an MainActivity Object
//        MainActivity mainAct = new MainActivity();

        MainActivity.startAlarms(context);

        NotificationHelper.push(context, "Rechat", "Aplicativo iniciado no boot.");
    }

}