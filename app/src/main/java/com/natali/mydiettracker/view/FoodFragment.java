package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.viewmodel.FoodViewModel;


public class FoodFragment extends Fragment {

    private FoodViewModel foodViewModel;

    EditText editText;
    TextView textViewDate;
    TextView textViewCalorie;
    AppCompatButton button;
    double total=0;

    public View onCreateView(@NonNull LayoutInflater inflater,   ViewGroup container, Bundle savedInstanceState) {

        foodViewModel =new ViewModelProvider(this).get(FoodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_food, container, false);

        editText=root.findViewById(R.id.foodName);
        textViewDate=root.findViewById(R.id.textViewDate);
        textViewDate.setText(foodViewModel.setDate());

        button=root.findViewById(R.id.addWeight);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findFood();
            }
        });

        textViewCalorie=root.findViewById(R.id.TextViewCalorie);

        foodViewModel.getSearchedFood().observe(getViewLifecycleOwner(), food-> {
            textViewCalorie.setText((String.valueOf(food.getCalorie())));
         foodViewModel.addFoodToDiary(food);

            total=total+food.getCalorie();
            System.out.println(total);

            textViewCalorie.setText((food.getName()+":"+String.valueOf(food.getCalorie()))+"\n\nTotal Calories: "+total);

            System.out.println(food.getName());
            System.out.println(food.getCalorie());
        });


        return root;
    }

    public void findFood() {

            if( editText.length() == 0 || editText.equals("") || editText == null){
                textViewCalorie.setText("Please enter food name!!");
            }
            else{
                foodViewModel.searchForFoodByName(editText.getText().toString());
                editText.setText("");
            }

    }

}
