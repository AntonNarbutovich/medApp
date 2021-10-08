package com.example.medappv2.ui.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medappv2.R;
import com.example.medappv2.databinding.FragmentTestScreenBinding;

public class MedicalTestScreenFragment extends Fragment {
    private FragmentTestScreenBinding binding;
    private String date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestScreenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        date = MedicalTestScreenFragmentArgs.fromBundle(getArguments()).getDate();
    }
}
