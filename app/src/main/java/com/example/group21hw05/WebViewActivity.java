package com.example.group21hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    ProgressDialog builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle("web View");
        builder = new ProgressDialog(WebViewActivity.this);
        builder.setMessage("Loading Page");
        builder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //builder.setCancelable(false);

        final WebView browser = (WebView) findViewById(R.id.webview);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);

        String url;
        url = getIntent().getExtras().getString("newsurl");
        builder.show();
        browser.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                builder.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                builder.dismiss();




            }

            //finish();
        });
        browser.loadUrl(url);
    }
    private class webviewclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

                builder.dismiss();


            return  true;
        }
    }
}
