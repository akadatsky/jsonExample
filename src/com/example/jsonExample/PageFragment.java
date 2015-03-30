package com.example.jsonExample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class PageFragment extends Fragment {

    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        Glide.with(getActivity()).load(url).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(imageView);


        return view;
    }

}