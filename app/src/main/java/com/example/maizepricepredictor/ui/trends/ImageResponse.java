package com.example.maizepricepredictor.ui.trends;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {
    @SerializedName("image_url")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
