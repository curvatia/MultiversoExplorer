package com.example.multiversoexplorer.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.multiversoexplorer.R;

public class WebviewActivity extends AppCompatActivity {

    private WebView wvPaginaweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wvPaginaweb = findViewById(R.id.wvPaginaweb);
        wvPaginaweb.loadUrl("https://www.multiversoexplorer.com/terminos-y-condiciones-generales/");
        wvPaginaweb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}