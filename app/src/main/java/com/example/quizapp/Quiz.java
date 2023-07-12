package com.example.quizapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class Quiz extends Fragment {

    Context context;
    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] rootLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] grassLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";
    int questionCount;

    DbHandler dBHandler;
    public Quiz(Context context) {
        this.context = context;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);
//        DbHandler dbHandler = new DbHandler(context);
//
//
//        letterTextView = view.findViewById(R.id.letter_text_view);
//        letterTextView.setText(getRandomLetter());
//
//        answerTextView = view.findViewById(R.id.answer_text_view);
//
//        Button skyButton = view.findViewById(R.id.sky_button);
//
////        for(int i = 0; i< 5;i++) {
////            new Handler().postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    letterTextView.setText(getRandomLetter());
////                    answerTextView.setText("");
////                }
////            }, 5000); // 5000 milliseconds = 5 seconds
////        }
//            skyButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(dbHandler.getId() <= 4) {
//                        //answerTextView.setText("Awesome your answer is right");
//                        dbHandler.insertQuestion(letterTextView.getText().toString());
//                        dbHandler.insertActualAnswer(answerString);
//                        dbHandler.insertUserAns(skyButton.getText().toString());
//
//                        // Wait for 5 seconds and create a new question
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                for (int i = 0; i < 5; i++) {
//                                    letterTextView.setText(getRandomLetter());
//                                    answerTextView.setText("");
//                                }
//                            }
//                        }, 5000);
//                    }// 5000 milliseconds = 5 seconds
//                    else if (dbHandler.getId() == 5) {
//                        List<Questions> list = dbHandler.getAnswers();
//                    }
//                }
//            });
//
//            Button grassButton = view.findViewById(R.id.grass_button);
//            grassButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dbHandler.insertQuestion(letterTextView.getText().toString());
//                    dbHandler.insertActualAnswer(answerString);
//                    dbHandler.insertUserAns(grassButton.getText().toString());
//                    // Wait for 5 seconds and create a new question
////                new Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        for(int i = 0;i<5;i++) {
////                            letterTextView.setText(getRandomLetter());
////                            answerTextView.setText("");
////                        }
////                    }
////                }, 5000); // 5000 milliseconds = 5 seconds
//                }
//            });
//
//            Button rootButton = view.findViewById(R.id.root_button);
//            rootButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dbHandler.insertQuestion(letterTextView.getText().toString());
//                    dbHandler.insertActualAnswer(answerString);
//                    dbHandler.insertUserAns(rootButton.getText().toString());
//                    // Wait for 5 seconds and create a new question
////                new Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        for(int i = 0; i<5;i++) {
////                            letterTextView.setText(getRandomLetter());
////                            answerTextView.setText("");
////                        }
////                    }
////                }, 5000); // 5000 milliseconds = 5 seconds
//                }
//            });


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        letterTextView = view.findViewById(R.id.letter_text_view);

        Button skyButton = view.findViewById(R.id.sky_button);
        Button grassButton = view.findViewById(R.id.grass_button);
        Button rootButton = view.findViewById(R.id.root_button);

        dBHandler = new DbHandler(requireContext());

        skyButton.setOnClickListener(v -> checkAnswer("Sky Letter"));
        grassButton.setOnClickListener(v -> checkAnswer("Grass Letter"));
        rootButton.setOnClickListener(v -> checkAnswer("Root Letter"));

        questionCount = 0;

        displayRandomLetter();
    }

    private void displayRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        letterTextView.setText(String.valueOf(letter));
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer(String selectedAnswer) {
        questionCount++;
//        boolean isCorrect = selectedAnswer.equals(answerString);
//        String message;
//        if (isCorrect) {
//            message = "Awesome! Your answer is correct.";
//            insertQuestionScore(letterTextView.getText().toString(), ); // Insert the score of 1 for a correct answer
//        } else {
//            message = "Incorrect! The answer is " + answerString + ".";
//            insertQuestionScore(0); // Insert the score of 0 for an incorrect answer
//        }
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

        insertQuestionScore(letterTextView.getText().toString(), answerString, selectedAnswer);

        // Wait for 2 seconds and display a new letter
        new Handler().postDelayed(() -> {
            if (questionCount < 5) {
                displayRandomLetter();
            } else {
                finishQuiz();
            }
        }, 2000);
    }

    private void insertQuestionScore(String q, String a1, String a2) {
        // databaseHelper.deleteAllQuizResults();
//        dBHandler.insertQuestion(q);
//        dBHandler.insertActualAnswer(a1);
//        dBHandler.insertUserAns(a2);
        dBHandler.insertEverything(q, a1, a2);
    }

    private void finishQuiz() {
        String completionMessage = "Quiz completed!";
        Toast.makeText(requireContext(), completionMessage, Toast.LENGTH_SHORT).show();

        //FragmentManager fm = getSupportFra
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);

        // Finish the current activity (QuizActivity)
        requireActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dBHandler.close();
    }

    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        return String.valueOf(letter);
    }
}
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }
}