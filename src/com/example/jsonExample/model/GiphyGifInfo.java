package com.example.jsonExample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiphyGifInfo implements Serializable {

    private String id;

    @SerializedName("embed_url")
    private String embedUrl;
    private GiphyImages images;

    public GiphyGifInfo() {
    }

    public String getId() {
        return id;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public GiphyImages getImages() {
        return images;
    }
}
