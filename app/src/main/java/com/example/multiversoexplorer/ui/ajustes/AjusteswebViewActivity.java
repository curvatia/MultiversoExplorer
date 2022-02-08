package com.example.multiversoexplorer.ui.ajustes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.multiversoexplorer.R;

public class AjusteswebViewActivity extends AppCompatActivity {

    private WebView AjustesWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustesweb_view);

        AjustesWebView = findViewById(R.id.HomeWebView);
        AjustesWebView.loadUrl("https://www.multiversoexplorer.com/politica-cookies/");
        AjustesWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}