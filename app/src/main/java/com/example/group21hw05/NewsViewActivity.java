package com.example.group21hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsViewActivity extends AppCompatActivity implements GetNewsAsync.InewsData {
    ProgressDialog builder;
    ArrayAdapter<Source> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("news");
        setContentView(R.layout.activity_news_view);
        builder = new ProgressDialog(NewsViewActivity.this);
        builder.setMessage("Loading News");
        builder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        builder.setCancelable(false);
        builder.show();

        String id=getIntent().getExtras().getString("sourceid");
        String url="https://newsapi.org/v2/top-headlines?sources="+id+"&apiKey=8f557fd881474e2e97e0568c454f5de3";
        new GetNewsAsync(NewsViewActivity.this).execute(url);
    }

    @Override
    public void getNewsData( final ArrayList<News> newsArrayList) {
        ListView listView = (ListView) findViewById(R.id.lv_2);
        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.news_items, newsArrayList);
        listView.setAdapter(newsAdapter);
        builder.dismiss();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String urltoweb=newsArrayList.get(position).url;
                Intent i=new Intent(NewsViewActivity.this,WebViewActivity.class);
                i.putExtra("newsurl",urltoweb);
                startActivity(i);

            }
        });
    }
}
