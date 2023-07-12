package com.example.quizapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptar extends RecyclerView.Adapter<RecyclerViewAdaptar.ViewHolder>{

    private final Context context;
    private final List<Quesions.Questions> quizResults;

    public RecyclerViewAdaptar(Context context, List<Quesions.Questions> l){
        this.context = context;
        quizResults = l;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_result_item, parent, false);
        return new ViewHolder(itemView);
    }