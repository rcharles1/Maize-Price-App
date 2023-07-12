package com.example.maizepricepredictor.ui.trends;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("image/1")
    Call<TrendsFragment.ImageResponse> getImage();
}
