package com.example.quizapp;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class QuizResult extends Fragment {
    DbHandler dbHandler;
    public QuizResult() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        dbHandler = new DbHandler(requireContext());

        List<Questions> quizResults = dbHandler.getAnswers();
        dbHandler.removeAllEntriesFromTable();
        if (quizResults.isEmpty()) {
            Toast.makeText(requireContext(), "No quiz results available.", Toast.LENGTH_SHORT).show();
        } else {
            RecyclerViewAdaptar quizResultAdapter = new RecyclerViewAdaptar(requireContext(), quizResults);
            recyclerView.setAdapter(quizResultAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbHandler.close();
    }
}
