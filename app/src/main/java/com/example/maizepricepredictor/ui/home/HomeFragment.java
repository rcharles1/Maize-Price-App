package com.example.maizepricepredictor.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maizepricepredictor.R;
import com.example.maizepricepredictor.databinding.FragmentHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().toString();
        binding.textView5.setText(month + "" + "2023");

        binding.priceInfo.setText("Price will rise by 2 percent");

//        Thread thread = new Thread(() -> {
//            try {
//                URL url = new URL("http://192.168.116.166/info/1");
//
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//
//                int responseCode = connection.getResponseCode();
//
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//
//                    String responseData = response.toString();
//
//                    // Parse the JSON response
//                    try {
//                        JSONObject jsonObject = new JSONObject(responseData);
//
//                        String info = jsonObject.getString("info");
//
//                        requireActivity().runOnUiThread(() -> {
//                            binding.priceInfo.setText(info);
//                        });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    requireActivity().runOnUiThread(() -> {
//                        Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
//                    });
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        thread.start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
