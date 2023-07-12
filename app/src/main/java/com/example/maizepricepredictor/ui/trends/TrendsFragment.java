package com.example.maizepricepredictor.ui.trends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maizepricepredictor.R;
import com.example.maizepricepredictor.databinding.FragmentDashboardBinding;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendsFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendsViewModel trendsViewModel =
                new ViewModelProvider(this).get(TrendsViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        imageView = rootView.findViewById(R.id.imageView); // Assuming you have an ImageView in your layout

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API service interface
        ApiService apiService = retrofit.create(ApiService.class);

        // Make API call
        Call<ImageResponse> call = apiService.getImage();
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    ImageResponse imageResponse = response.body();
                    String imageUrl = imageResponse.getImageUrl();

                    Picasso.get().load(imageUrl).into(imageView);
                } else {
                    // Handle error response
                }
            }
            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                // Handle network error
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class ImageResponse {
        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
