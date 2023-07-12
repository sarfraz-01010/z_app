package com.example.quizapp;

public class Quesions {
    public class Questions {
        public int id;
        public String question;
        public String correct_answer;

        public String user_answer;

        public Questions() {

        }

        public Questions(int id, String q, String a1, String a2) {
            this.id = id;
            question = q;
            correct_answer = a1;
            user_answer = a2;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQuestion() {
            return this.question;
        }

        public void setCorrectAnswer(String answer) {
            this.correct_answer = answer;
        }

        public String getCorrectAnswer() {
            return this.correct_answer;
        }

        public void setUserAnswer(String answer) {
            this.user_answer = answer;
        }

        public String getUserAnswer() {
            return this.user_answer;
        }

    }
    }
