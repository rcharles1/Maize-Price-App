package com.example.maizepricepredictor.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maizepricepredictor.R;
import com.example.maizepricepredictor.databinding.FragmentNotificationsBinding;
import com.example.maizepricepredictor.ui.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private Button myButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        myButton = binding.button2;
        myButton.setOnClickListener(v -> {
            // Handle button click here
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);

        });
        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}