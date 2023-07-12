package com.example.quizapp;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FragementAssignmentDB";
    private static final String TABLE_NAME = "result";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER_ENTERED = "answerentered";
    private static final String COLUMN_ANSWER_CORRECT = "answercorrect";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_ANSWER_ENTERED + " TEXT,"
                + COLUMN_ANSWER_CORRECT + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean insertQuestion(String question){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public boolean insertEverything(String question, String cAns, String yAns){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);
        values.put(COLUMN_ANSWER_CORRECT, cAns);
        values.put(COLUMN_ANSWER_ENTERED, yAns);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public boolean insertUserAns(String answer){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANSWER_ENTERED, answer);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public boolean insertActualAnswer(String answer){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANSWER_CORRECT, answer);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public List<Questions> getAnswers() {
        List<Questions> questions = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {

                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());

        }
        * */

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String q = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
                @SuppressLint("Range")  String a1 = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_CORRECT));
                @SuppressLint("Range") String a2 = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_ENTERED));
                questions.add(new Questions(id, q, a1, a2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return questions;
    }

    public int getId(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMN_ID};
        String sortOrder = COLUMN_ID + " DESC";
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, sortOrder);

        int lastEntryId = -1;

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int l = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            lastEntryId = l;
        }

        cursor.close();

        return lastEntryId;
    }

    public void removeAllEntriesFromTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        if (rowsDeleted > 0) {
            // Deletion successful
            Log.d("Database", "All entries deleted from table: " + TABLE_NAME);
        } else {
            // No rows deleted or an error occurred
            Log.e("Database", "Error deleting entries from table: " + TABLE_NAME);
        }
    }
}