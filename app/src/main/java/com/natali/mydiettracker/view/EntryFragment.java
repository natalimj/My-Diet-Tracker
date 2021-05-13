package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.model.Exercise;
import com.natali.mydiettracker.model.Food;
import com.natali.mydiettracker.viewmodel.EntryViewModel;


import java.util.ArrayList;
import java.util.List;

public class EntryFragment extends Fragment {

    private EntryViewModel entryViewModel;

    private EditText editText;
    private TextView textViewDate;
    private TextView textWarning;
    private TextView textWaterAmount;
    private AppCompatButton foodButton;
    private AppCompatButton waterButton;
    private AutoCompleteTextView editTextExercise;
    private EditText editTextDuration;
    private Button exerciseButton;
    private List<String> categories= new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        entryViewModel =new ViewModelProvider(this).get(EntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_entry, container, false);

        editText=root.findViewById(R.id.foodName);
        textViewDate=root.findViewById(R.id.textViewDate);
        textViewDate.setText(entryViewModel.setDate());
        textWarning=root.findViewById(R.id.textWarning);
        textWaterAmount=root.findViewById(R.id.textWaterAmount);

        foodButton=root.findViewById(R.id.addFood);
        waterButton=root.findViewById(R.id.addWater);
        exerciseButton=root.findViewById(R.id.addExercise);

        editTextExercise=root.findViewById(R.id.exerciseName);
        editTextDuration=root.findViewById(R.id.exerciseDuration);

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findFood();
            }
        });

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWater();
            }
        });

        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

        entryViewModel.getWaterAmount().observe(getViewLifecycleOwner(), water->{
            textWaterAmount.setText(water.toString());
        });


        entryViewModel.getExercises().observe(getViewLifecycleOwner(), exercises->{
            for(Exercise exercise:exercises){
                categories.add(exercise.getExerciseName().toLowerCase());
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        editTextExercise.setAdapter(dataAdapter);

        entryViewModel.getSearchedExercise().observe(getViewLifecycleOwner(), exercise-> {
            entryViewModel.addExerciseToDiary(exercise,Integer.valueOf(editTextDuration.getText().toString().trim()));
            editTextDuration.setText("");
            editTextExercise.setText("");
        });

        return root;
    }

    public void findFood() {

        if( editText.length() == 0 || editText.equals("") || editText == null){
            textWarning.setText("Please enter food name!!");
        }
        else{
            entryViewModel.searchForFoodByName(editText.getText().toString());
             editText.setText("");

            entryViewModel.getSearchedFood().observe(getViewLifecycleOwner(), new Observer<Food>(){
                public void onChanged(@Nullable Food food) {
                    if (food == null)
                        textWarning.setText("Please enter a valid food name");
                    else {
                        entryViewModel.addFoodToDiary(food);
                        Toast.makeText(getContext(), food.getName() + " has been added to your list", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void addWater(){
        entryViewModel.addWater();
        Toast.makeText(getContext(), "A glass of water has been added to your list", Toast.LENGTH_LONG).show();
    }

    public void addExercise() {
        if( editTextExercise.getText().length() == 0 || editTextExercise.getText().equals("") || editTextExercise.getText() == null){
            textWarning.setText("Please enter exercise name!!");
        }
        if( editTextDuration.getText().length() == 0 || editTextDuration.getText().equals("") || editTextDuration.getText() == null){
            textWarning.setText("Please enter exercise time!!");
        }
        if (categories.contains(editTextExercise.getText())){
            textWarning.setText("Invalid exercise name!!");
        }
        else{
            entryViewModel.searchExerciseByName(editTextExercise.getText().toString().toLowerCase());
        }
    }
}
