package com.natali.mydiettracker.data.entry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natali.mydiettracker.R;
import com.natali.mydiettracker.model.Entry;

import java.util.ArrayList;

public class WeightEntryAdapter extends RecyclerView.Adapter<WeightEntryAdapter.ViewHolder> {

    ArrayList<Entry> entries;
    public WeightEntryAdapter(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public WeightEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.entry_list_item, parent, false);
        return new WeightEntryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightEntryAdapter.ViewHolder holder, int position) {
        holder.date.setText(entries.get(position).getDateString());
        holder.info.setText(String.valueOf(entries.get(position).getCalorie()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView info;

        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.entry_name);
            info = itemView.findViewById(R.id.entry_info);
        }
    }

}
