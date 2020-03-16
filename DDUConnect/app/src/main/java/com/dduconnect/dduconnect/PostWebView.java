package com.dduconnect.dduconnect;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class PostWebView extends AppCompatActivity {
    WebView web ;
    ImageView no_internet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        final ProgressBar progressBar=findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle(" ");
        no_internet=findViewById(R.id.no_internet2);
        no_internet.setVisibility(View.VISIBLE);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        web = (WebView) findViewById(R.id.gallery_webview);
        web.getSettings().setJavaScriptEnabled(true);

        if(Connections.isOnline(getApplicationContext()))
        {
            no_internet.setVisibility(View.GONE);
            Intent i=getIntent();
            String link =i.getExtras().getString("itemlink");
            String link_allowed =i.getExtras().getString("allowed","false");
            if(link_allowed.equals("true")){

                web.getSettings().getJavaScriptEnabled();
                web.setWebViewClient(new WebViewClient(){
                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        progressBar.setVisibility(View.VISIBLE);
                        return false;
                    }
                });
                //  web.loadUrl("https://dduconnect.in/19th-convocation/");
                web.loadUrl(link);
            }
            else {
                web.getSettings().getJavaScriptEnabled();
                web.setWebViewClient(new WebViewClient(){
                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                        return true;
                    }
                });
                //  web.loadUrl("https://dduconnect.in/19th-convocation/");
                web.loadUrl(link);
            }


        }else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();

        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        if (web.canGoBack()) {
            web.goBack();
        } else {
            finish();
        }
        return true;
    }

}
