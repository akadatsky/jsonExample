package com.example.jsonExample.model;

import java.io.Serializable;
import java.util.List;

public class GiphyResponse implements Serializable {

    private List<GiphyGifInfo> data;
    private GiphyPagination pagination;

    public GiphyResponse() {
    }

    public List<GiphyGifInfo> getData() {
        return data;
    }

    public GiphyPagination getPagination() {
        return pagination;
    }
}
