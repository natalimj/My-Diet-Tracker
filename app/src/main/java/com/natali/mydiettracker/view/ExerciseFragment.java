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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.model.Exercise;
import com.natali.mydiettracker.viewmodel.ExerciseViewModel;

import java.util.ArrayList;
import java.util.List;


public class ExerciseFragment extends Fragment  {

    private ExerciseViewModel exerciseViewModel;
    private TextView textViewDate;
    private TextView textViewCalorie;
    private  AutoCompleteTextView editTextExercise;
    private EditText editTextDuration;
    private Button button;
    private String exercise;
    List<String> categories= new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise, container, false);


        textViewDate=root.findViewById(R.id.textViewDate);
        textViewCalorie=root.findViewById(R.id.textViewCalorie);
        editTextExercise=root.findViewById(R.id.exerciseName);
        editTextDuration=root.findViewById(R.id.exerciseDuration);

        textViewDate.setText(exerciseViewModel.setDate());

        exerciseViewModel.getExercises().observe(getViewLifecycleOwner(), exercises->{

            for(Exercise exercise:exercises){
                categories.add(exercise.getExerciseName().toLowerCase());
            }

        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        editTextExercise.setAdapter(dataAdapter);



        exerciseViewModel.getSearchedExercise().observe(getViewLifecycleOwner(), exercise-> {

            textViewCalorie.setText(exercise.getExerciseName()+" "+(String.valueOf(exercise.getCalorie())));

            exerciseViewModel.addExerciseToDiary(exercise,Integer.valueOf(editTextDuration.getText().toString().trim()));


           editTextDuration.setText("");
           editTextExercise.setText("");

        });

        button=root.findViewById(R.id.addExercise);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addExercise();
            }
        });

        return root;
    }


    public void addExercise() {

        if( editTextExercise.getText().length() == 0 || editTextExercise.getText().equals("") || editTextExercise.getText() == null){
            textViewCalorie.setText("Please enter exercise name!!");
        }
        if( editTextDuration.getText().length() == 0 || editTextDuration.getText().equals("") || editTextDuration.getText() == null){
            textViewCalorie.setText("Please enter exercise time!!");
        }
        if (categories.contains(editTextExercise.getText())){
            textViewCalorie.setText("Invalid exercise name!!");
        }

        else{
            exerciseViewModel.searchExerciseByName(editTextExercise.getText().toString().toLowerCase());
        }
    }

}

