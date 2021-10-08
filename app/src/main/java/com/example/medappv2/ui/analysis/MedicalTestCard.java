package com.example.medappv2.ui.analysis;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.example.medappv2.R;
import com.example.medappv2.utils.ViewAnimationUtils;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MedicalTestCard {
    private View view;
    private ConstraintLayout cardLayout;
    private ConstraintLayout expandableView;
    private Button expandBtn;
    private MaterialCardView cardView;
    private ListView dateList;
    TextView testName;
    ArrayList<String> items = new ArrayList<>();

    public MedicalTestCard(View v, String date, String test) {
        items.add(date);
        view = v;
        cardLayout = (ConstraintLayout) view.findViewById(R.id.cardLayout);
        expandableView = (ConstraintLayout) view.findViewById(R.id.expandableView);
        cardView = (MaterialCardView) view.findViewById(R.id.cardView);
        expandBtn = (Button) view.findViewById(R.id.expandBtn);
        dateList = (ListView) view.findViewById(R.id.dateList);
        testName = (TextView) view.findViewById(R.id.medicalTestName);
        testName.setText(test);

        expandBtn.setOnClickListener(x -> {
            if(expandableView.getVisibility() == View.GONE){
//                TransitionManager.beginDelayedTransition(expandableView, new AutoTransition());
//                expandableView.setVisibility(View.VISIBLE);
                ViewAnimationUtils.expand(expandableView);
                expandBtn.setBackgroundResource(R.drawable.arrow_up);
            } else {
//                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                expandableView.setVisibility(View.GONE);
                ViewAnimationUtils.collapse(expandableView);
                expandBtn.setBackgroundResource(R.drawable.arrow_down);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_expandable_list_item_1, items);
        dateList.setAdapter(adapter);

        dateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExaminationsFragmentDirections.NavigateToDescription action =
                        ExaminationsFragmentDirections.navigateToDescription(items.get(position));
                Fragment fragment = FragmentManager.findFragment(parent);
                NavHostFragment.findNavController(fragment).navigate(action);
            }
        });
    }
}
