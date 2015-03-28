package com.example.jsonExample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiphyPagination implements Serializable {

    @SerializedName("total_count")
    private int totalCount;

    public GiphyPagination() {
    }

    public int getTotalCount() {
        return totalCount;
    }

}
