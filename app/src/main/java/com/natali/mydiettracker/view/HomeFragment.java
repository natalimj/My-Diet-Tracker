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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.data.entry.EntryAdapter;
import com.natali.mydiettracker.model.Entry;
import com.natali.mydiettracker.viewmodel.HomeViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener  {

    private HomeViewModel homeViewModel;

    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    CardView card5;
    CardView card6;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        card1=root.findViewById(R.id.cardView1);
        card2=root.findViewById(R.id.cardView2);
        card3=root.findViewById(R.id.cardView3);
        card4=root.findViewById(R.id.cardView4);
        card5=root.findViewById(R.id.cardView5);
        card6=root.findViewById(R.id.cardView6);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.cardView1:
                fragment = new FoodFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView2:
                fragment = new ExerciseFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView3:
                fragment = new WaterFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView4:
                fragment = new ProgressFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView5:
                fragment = new PlanFragment();
                replaceFragment(fragment);
                break;

            case R.id.cardView6:
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