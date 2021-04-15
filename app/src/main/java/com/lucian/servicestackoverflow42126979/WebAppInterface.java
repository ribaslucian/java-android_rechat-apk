package com.lucian.servicestackoverflow42126979;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
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

    // login
    @JavascriptInterface
    public void setUserId(String user_id) {
//        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("user_id", user_id);
//        editor.commit();
//        editor.apply();

//        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("user_id", user_id);
//        editor.commit();

//        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("rechat",  Context.MODE_PRIVATE);
//        String _user_id = MainActivity.preferences.getString("user_id","0");

//        Toast.makeText(mContext, "Setando id do usuariooo " + _user_id, Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "Setando id do usuario " + user_id, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", user_id);
        editor.commit();
//        editor.apply();
    }



    // logout
    @JavascriptInterface
    public void login(String cookie) {
        Toast.makeText(mContext, "Efetuando login no cookie webview", Toast.LENGTH_SHORT).show();

        // salvar cookie no celular
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("rechat_session", cookie);
        editor.commit();

        // inserindo cookie no navegador
        CookieSyncManager.createInstance(MainActivity.self);
        CookieManager cookieManager = CookieManager.getInstance();
        CookieManager.getInstance().setCookie(MainActivity.url, "rechat_session=" + cookie + "; path=/");
    }

    // logout
    @JavascriptInterface
    public void logout() {
        Toast.makeText(mContext, "Efetuando LOGOUT no cookie webview", Toast.LENGTH_SHORT).show();

        // limpar cookie do celular
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("rechat_session");
        editor.commit();

        // limpando cookie
        CookieSyncManager.createInstance(MainActivity.self);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }


}