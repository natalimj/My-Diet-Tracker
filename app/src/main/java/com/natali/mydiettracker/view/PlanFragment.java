package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.viewmodel.MainActivityViewModel;
import com.natali.mydiettracker.R;
import com.natali.mydiettracker.model.UserInfo;
import com.natali.mydiettracker.viewmodel.PlanViewModel;

public class PlanFragment extends Fragment {

    private PlanViewModel planViewModel;
    private MainActivityViewModel mainActivityViewModel;

    private EditText age;
    private EditText weight;
    private EditText height;
    private TextView idealWeight;
    private TextView dailyCalories;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Spinner spinner;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private AppCompatButton button;
    private AppCompatButton button2;
    private  int ageValue;
    private int weightValue;
    private int heightValue;
    boolean validPlan;
    private ArrayAdapter<String> dataAdapter;
    private String[] activities={"Lightly active","Moderately active","Active","Very Active"};
    private String activityLevel;
    private String gender;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        mainActivityViewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);

        linearLayout=root.findViewById(R.id.planLayout);
        linearLayout2=root.findViewById(R.id.planLayout2);
        age=root.findViewById(R.id.age);
        weight=root.findViewById(R.id.startWeight);
        height=root.findViewById(R.id.height);

        idealWeight=root.findViewById(R.id.targetWeightText);
        dailyCalories=root.findViewById(R.id.dailyCalorieText);
        spinner=root.findViewById(R.id.spinner);


        button=root.findViewById(R.id.addUserInfo);
        button2=root.findViewById(R.id.addNewUserInfo);
        radioGroup=root.findViewById(R.id.radioGender);


        spinner.setPrompt("Select Activity Level");
        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, activities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //idealWeight.setText(parent.getSelectedItem().toString());
                activityLevel=parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageValue=Integer.valueOf(age.getText().toString());
                weightValue=Integer.valueOf(weight.getText().toString());
                heightValue=Integer.valueOf(height.getText().toString());
                if(validPlan==false){
                    addUserInfo();
                    validPlan=true;
                }
                 else{
                    updateUserInfo();
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout2.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                System.out.println("set new values");
                validPlan=true;
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton= root.findViewById(selectedId);
                switch(radioButton.getId()) {
                    case R.id.radioMale:
                            gender="male";
                            break;
                    case R.id.radioFemale:
                           gender="female";
                            break;
                }
            }
        });

        planViewModel.getDailyCalories().observe(getViewLifecycleOwner(), calorie->
        {
            if(calorie==null||calorie==0){
                linearLayout2.setVisibility(View.INVISIBLE);
               linearLayout.setVisibility(View.VISIBLE);
              System.out.println("set new values");
            }
            else {

             linearLayout.setVisibility(View.INVISIBLE);
             linearLayout2.setVisibility(View.VISIBLE);
             dailyCalories.setVisibility(View.VISIBLE);
                dailyCalories.setText( calorie.toString());
                System.out.println("daily calories: "+ calorie.toString());
            }

        });

        planViewModel.getTargetWeight().observe(getViewLifecycleOwner(),target->
        {
            if(target==null||target==0){
                System.out.println("do something there target");
            }
            else {
                idealWeight.setText(String.format("%.1f", target));
            }
        });

        return root;
    }

    public void addUserInfo(){

        planViewModel.addUserInfo(new UserInfo("userId",gender,ageValue,weightValue,0,heightValue,activityLevel,0));
    }

    public void updateUserInfo(){
        planViewModel.updateUserInfo(new UserInfo("userId",gender,ageValue,weightValue,0,heightValue,activityLevel,0));
        System.out.println("User plan is updated!");
    }

    public void setView(View view){
        if(validPlan==true){
            linearLayout.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dailyCalories.setVisibility(View.VISIBLE);
        }
        else{
            linearLayout2.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            System.out.println("set new values");
        }
    }

}
