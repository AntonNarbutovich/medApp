package com.example.medappv2.ui.analysis;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.medappv2.R;
import com.example.medappv2.databinding.FragmentExaminationsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ExaminationsFragment extends Fragment {

    private ExaminationsViewModel examinationsViewModel;
    private FragmentExaminationsBinding binding;
    private ArrayList<MedicalTestCard> cards = new ArrayList<>();
    private String[] testNames = new String[]{"Test 1", "Test 2", "Test 3"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        examinationsViewModel =
                new ViewModelProvider(this).get(ExaminationsViewModel.class);

        binding = FragmentExaminationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        examinationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        FloatingActionButton addBtn = ((FloatingActionButton)getActivity().findViewById(R.id.add_analysis_btn));
        View popupWindow = getActivity().findViewById(R.id.add_analysis_popup);
        Button popupBtn = popupWindow.findViewById(R.id.addBtn);
        Spinner testNameSelector = (Spinner) popupWindow.findViewById(R.id.nameSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, testNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        testNameSelector.setAdapter(adapter);

        popupBtn.setOnClickListener(view1 -> {
            LinearLayout optionsLayout = (LinearLayout) getView().findViewById(R.id.medicalLayout);
            DatePicker datePicker = (DatePicker) popupWindow.findViewById(R.id.datePicker);
            View toAdd = inflater.inflate(R.layout.medical_test_card, optionsLayout,false);
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(calendar.getTime());

            boolean addFlag = true;
            for(MedicalTestCard c: cards){
                if(c.testName.getText().equals((String) testNameSelector.getSelectedItem())){
                    c.items.add(dateString);
                    addFlag = false;
                    break;
                }
            }
            if(addFlag){
                cards.add(new MedicalTestCard(toAdd, dateString, (String) testNameSelector.getSelectedItem()));
                optionsLayout.addView(toAdd);
            }

            popupWindow.setVisibility(View.GONE);
        });

        addBtn.setOnClickListener(view12 -> getActivity().findViewById(R.id.add_analysis_popup).setVisibility(View.VISIBLE));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}