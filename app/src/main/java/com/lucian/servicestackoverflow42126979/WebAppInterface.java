package com.lucian.servicestackoverflow42126979;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void setUserId(String user_id) {
//        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("user_id", user_id);
//        editor.commit();
//        editor.apply();

        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.commit();

//        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
//        String _user_id = MainActivity.preferences.getString("user_id","0");

//        Toast.makeText(mContext, "Setando id do usuariooo " + _user_id, Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "Setando id do usuario " + user_id, Toast.LENGTH_SHORT).show();
    }
}