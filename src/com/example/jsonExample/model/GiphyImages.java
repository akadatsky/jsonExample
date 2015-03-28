package com.example.jsonExample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiphyImages implements Serializable {

    @SerializedName("fixed_height")
    GiphyFinalImageInfo fixedHeight;

    @SerializedName("fixed_width")
    GiphyFinalImageInfo fixedWidth;

    GiphyFinalImageInfo original;

    @SerializedName("fixed_height_downsampled")
    GiphyFinalImageInfo fixedHeightDownsampled;


    public GiphyImages() {
    }

    public GiphyFinalImageInfo getFixedHeight() {
        return fixedHeight;
    }

    public GiphyFinalImageInfo getFixedWidth() {
        return fixedWidth;
    }

    public GiphyFinalImageInfo getOriginal() {
        return original;
    }

    public GiphyFinalImageInfo getFixedHeightDownsampled() {
        return fixedHeightDownsampled;
    }
}
