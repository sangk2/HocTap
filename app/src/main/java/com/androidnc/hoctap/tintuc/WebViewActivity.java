package com.androidnc.hoctap.tintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidnc.hoctap.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView =findViewById(R.id.webView);
        intent = getIntent();
        String link = intent.getStringExtra("openLink");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}