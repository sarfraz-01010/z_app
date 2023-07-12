package com.example.quizapp;

public class Quesions {
    public class Questions {
        public int id;
        public String question;
        public String correct_answer;

        public String user_answer;

        public Questions(){

        }

        public Questions(int id, String q, String a1, String a2){
            this.id = id;
            question = q;
            correct_answer = a1;
            user_answer = a2;
        }

    }
