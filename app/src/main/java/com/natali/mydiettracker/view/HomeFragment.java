package com.natali.mydiettracker.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.viewmodel.HomeViewModel;
import com.natali.mydiettracker.viewmodel.PlanViewModel;

public class HomeFragment extends Fragment implements View.OnClickListener  {

    private HomeViewModel homeViewModel;
    private PlanViewModel planViewModel;

    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    TextView textView;
    TextView textView2;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);


        //TODO: change names in home page
        card1=root.findViewById(R.id.cardView1);
        card2=root.findViewById(R.id.cardView2);
        card3=root.findViewById(R.id.cardView3);
        card4=root.findViewById(R.id.cardView4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        textView=root.findViewById(R.id.textView);
        textView2=root.findViewById(R.id.textTarget);

        planViewModel.getDailyCalories().observe(getViewLifecycleOwner(), calorie->
        {
            if(calorie==null||calorie==0){
                System.out.println("set new values");
                textView.setText("CREATE DIET PLAN");

            }
            else {
                textView.setText("TARGET DAILY CALORIES: "+calorie.toString());
                System.out.println("daily calories: "+ calorie.toString());
            }

        });


        planViewModel.getTargetWeight().observe(getViewLifecycleOwner(),target->
        {
            if(target==null||target==0){
                textView2.setText("AND START");
            }
            else {
               textView2.setText("TARGET WEIGHT: "+String.format("%.1f", target));
            }
        });


        return root;
    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.cardView1:
                fragment = new EntryFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView2:
                fragment = new ProgressFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView3:
                fragment = new PlanFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView4:
                fragment = new DiaryFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}