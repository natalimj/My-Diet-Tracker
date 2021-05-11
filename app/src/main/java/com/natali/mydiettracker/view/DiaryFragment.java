package com.natali.mydiettracker.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.data.entry.EntryAdapter;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.viewmodel.DiaryViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DiaryFragment extends Fragment implements DatePickerDialog.OnDateSetListener{


    private DiaryViewModel diaryViewModel;
    private Button button;
    private TextView textView;
    private TextView food;
    private TextView exercise;
    private TextView water;
    private TextView weight;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private EntryAdapter entryAdapter;
    private TextView textUserInfo;
    private double foodCal;
    private double exerciseCal;
    private double totalCal;
    String strDate;

    ArrayList<Entry> foodList = new ArrayList<>();
    ArrayList<Entry> exerciseList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_diary, container, false);
        diaryViewModel=new ViewModelProvider(this).get(DiaryViewModel.class);

        button=root.findViewById(R.id.buttonDate);
        textView=root.findViewById(R.id.textViewDate);

        food=root.findViewById(R.id.foodText);
        exercise=root.findViewById(R.id.exerciseText);
        water=root.findViewById(R.id.waterText);
        weight=root.findViewById(R.id.weightText);
        textUserInfo=root.findViewById(R.id.textUserInfo);

        recyclerView = root.findViewById(R.id.recView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        recyclerView2=root.findViewById(R.id.recView2);
        recyclerView2.hasFixedSize();
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        diaryViewModel.setSelectedDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

        updateView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(DiaryFragment.this, 0);
                datePicker.show(getParentFragmentManager(), "date picker");
            }
        });

        return root;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = format.format(calendar.getTime());
        diaryViewModel.setSelectedDate(strDate);
        updateView();
    }

    public void updateView(){
        food.setText("");
        exercise.setText("");

        strDate="";
        diaryViewModel.getSelectedDate().observe(getViewLifecycleOwner(),new Observer<String>() {
            public void onChanged(@Nullable String date) {
                textView.setText(date);
                strDate=date;
            }
        });

        diaryViewModel.getWeight().observe(getViewLifecycleOwner(),new Observer<Entry>() {
            public void onChanged(@Nullable Entry entry) {
                if(entry!=null){
                    weight.setText(String.valueOf(entry.getCalorie()));
                }
                else{
                    weight.setText("No Weight Info Found");
                }

            }
        });

        diaryViewModel.getFoodsForUser().observe(getViewLifecycleOwner(), new Observer<List<Entry>>() {
            public void onChanged(@Nullable List<Entry> entries) {
                if (entries != null) {
                    foodList.clear();
                    for (Entry entry : entries) {
                        foodList.add(entry);
                    }
                    entryAdapter = new EntryAdapter(foodList);
                    recyclerView.setAdapter(entryAdapter);
                }
                if (foodList.isEmpty()) {
                    food.setText("No Food found");
                }
            }
        });

        diaryViewModel.getExercisesForUser().observe(getViewLifecycleOwner(), new Observer<List<Entry>>() {
            public void onChanged(@Nullable List<Entry> entries) {
                if (entries != null) {
                    exerciseList.clear();
                    for (Entry entry : entries) {
                        exerciseList.add(entry);
                        System.out.println(entry.getName());
                    }
                    entryAdapter = new EntryAdapter(exerciseList);
                    recyclerView2.setAdapter(entryAdapter);
                }
                if (exerciseList.isEmpty()) {
                    exercise.setText("No Exercise found");
                }
            }
        });

        diaryViewModel.getWaterForUser().observe(getViewLifecycleOwner(), new Observer<Integer>(){
            public void onChanged(@Nullable Integer waterAmount){
                if (waterAmount != null)
                    water.setText(waterAmount + " Glass");
                if (waterAmount == 0)
                    water.setText("No water found");
            }
        });


        // find how many calories are left for today : TARGET CALORIES - FOOD + EXERCISE
        diaryViewModel.getDailyCalories().observe(getViewLifecycleOwner(), calorie-> {
            totalCal=Double.valueOf(calorie);
            diaryViewModel.getTotalFoodCalories(diaryViewModel.getSelectedDate().getValue()).observe(getViewLifecycleOwner(), foodCalorie-> {
                if(foodCalorie!=null) {
                    foodCal = Double.valueOf(foodCalorie);
                }
                diaryViewModel.getTotalExerciseCalories(diaryViewModel.getSelectedDate().getValue()).observe(getViewLifecycleOwner(), exerciseCalorie->{
                    if(exerciseCalorie!=null) {
                        exerciseCal=Double.valueOf(exerciseCalorie);
                        if(strDate.equalsIgnoreCase(new SimpleDateFormat("dd-MM-yyyy").format(new Date())))
                            textUserInfo.setText("TARGET CALORIES - FOOD + EXERCISE=\n"+(totalCal+exerciseCal-foodCal)+ "\n CALORIES LEFT FOR TODAY");
                        else
                            textUserInfo.setText("");
                    }
                });
            });
        });

    }


}

