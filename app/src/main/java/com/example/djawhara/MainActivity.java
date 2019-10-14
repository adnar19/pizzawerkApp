package com.example.djawhara;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Window window=getWindow();

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.setStatusBarColor(getColor(R.color.mystatusbarColor));
        }else{
            window.setStatusBarColor(getResources().getColor(R.color.mystatusbarColor));
        }

        webv =(WebView)findViewById(R.id.wv);
        webv.getSettings().setJavaScriptEnabled(true);
        webv.getSettings().setDomStorageEnabled(true);
        webv.setWebViewClient(new WebViewClient());
        webv.getSettings().setAppCacheEnabled(false);
        webv.getSettings().setDomStorageEnabled(true);
        webv.setBackgroundColor(Color.parseColor("#90caf9"));
        webv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webv.addJavascriptInterface(new WebAppInterface(this),"Android");
        webv.loadUrl("http://eldjawhara.com/web/webapp");
    }
    public void vibration(){
        Vibrator V =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        V.vibrate(400);
    }


    public class WebAppInterface {

        Context mContext;

        WebAppInterface(Context c){
            mContext=c;
        }

        @JavascriptInterface
    public void vibrate(){
            vibration();
    }

    }
}
