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

import com.example.group21hw05.R;
import com.example.group21hw05.Source;

import java.util.List;

public class SourceAdapter extends ArrayAdapter<Source> {
    public SourceAdapter(@NonNull Context context, int resource, @NonNull List<Source> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Source source= getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.source_items,parent,false );
        }
        TextView textViewid=(TextView) convertView.findViewById(R.id.tv_id);


    textViewid.setText(source.getName());

        return convertView;

    }
}
