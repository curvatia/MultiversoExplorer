package com.example.multiversoexplorer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.multiversoexplorer.R;

public class HomeWebViewActivity extends AppCompatActivity {

    private WebView HomeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_view);

        HomeWebView = findViewById(R.id.HomeWebView);
        HomeWebView.loadUrl("https://www.multiversoexplorer.com/");
        HomeWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}