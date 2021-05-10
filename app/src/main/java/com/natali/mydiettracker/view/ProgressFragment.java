package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.data.entry.EntryAdapter;
import com.natali.mydiettracker.data.entry.WeightEntryAdapter;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.viewmodel.ProgressViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProgressFragment extends Fragment {

    private ProgressViewModel progressViewModel;
    private EditText editText;
    private Button button;
    private TextView textViewDate;
    private WeightEntryAdapter entryAdapter;
    private RecyclerView recyclerView;
    private Double weight=0.0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        progressViewModel =new ViewModelProvider(this).get(ProgressViewModel.class);

        View root = inflater.inflate(R.layout.fragment_progress, container, false);


        editText=root.findViewById(R.id.weight);
        button=root.findViewById(R.id.addWeight);

        textViewDate=root.findViewById(R.id.textViewDate);
        textViewDate.setText(progressViewModel.setDate());

/*
        progressViewModel.getWeightForGivenDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date())).observe(getViewLifecycleOwner(),
                new Observer<Entry>() {
                    public void onChanged(@Nullable Entry weightEntry) {
                     weight=weightEntry.getCalorie();
                    }
        });



*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addWeight(Double.parseDouble(editText.getText().toString()));
                    editText.setText("");
            }
        });

        recyclerView=root.findViewById(R.id.recView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        progressViewModel.getWeightForUser().observe(getViewLifecycleOwner(), entries->{
            ArrayList<Entry> weightList = new ArrayList<>();
            if (entries != null) {
                for (Entry entry : entries) {
                    weightList.add(entry);
                    System.out.println(entry.getName());
                }
                entryAdapter = new WeightEntryAdapter(weightList);
                recyclerView.setAdapter(entryAdapter);
            }
            if(weightList.isEmpty()){
            }
        });

        return root;
    }

    public void addWeight(double weight){
        progressViewModel.addWeight(weight);
    }

    public void updateWeight(Double weight){
        progressViewModel.updateWeight(weight);
    }
}
