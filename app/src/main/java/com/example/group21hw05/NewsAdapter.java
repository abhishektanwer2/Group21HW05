package com.example.group21hw05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        News news= getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.news_items,parent,false );
        }
        TextView textViewtitle=(TextView) convertView.findViewById(R.id.tv_title);
        TextView textViewauthor=(TextView) convertView.findViewById(R.id.tv_author);
        TextView textViewdate=(TextView) convertView.findViewById(R.id.tv_date);
        ImageView imageViewnews=(ImageView)convertView.findViewById(R.id.iv_news);

        textViewtitle.setText(news.title);
        textViewauthor.setText(news.author);
        textViewdate.setText(news.publishedAt);
        Picasso.get().load(news.urlToImage).into(imageViewnews);
        return convertView;
    }
}
