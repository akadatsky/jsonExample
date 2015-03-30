package com.example.jsonExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    public MyAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        String url = getItem(position);

        Glide.with(convertView.getContext()).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);

        return convertView;
    }
}
