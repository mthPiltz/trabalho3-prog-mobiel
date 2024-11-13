package com.example.trabalho3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho3.R;
import com.example.trabalho3.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getDatabase(getApplicationContext());
    }
}