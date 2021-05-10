package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.viewmodel.WaterViewModel;

public class WaterFragment extends Fragment {
    TextView textView1;
    TextView textView2;
   WaterViewModel waterViewModel;
   Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        waterViewModel =new ViewModelProvider(this).get(WaterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_water, container, false);

        textView1=root.findViewById(R.id.textView1);
       textView1.setText(waterViewModel.setDate());

     textView2=root.findViewById(R.id.textWaterAmount);

        button=root.findViewById(R.id.buttonPlus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addWater();
            }
        });

        waterViewModel.getWaterAmount().observe(getViewLifecycleOwner(), water->{
            textView2.setText(water.toString());
        });

        return root;
    }

    public void addWater(){
        waterViewModel.addWater();
    }

}