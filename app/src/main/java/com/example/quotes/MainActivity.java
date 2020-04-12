package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView, authorTextView, likesTextView, continueTextView;
    private ImageView likeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        likesTextView = findViewById(R.id.likesTextView);
        continueTextView = findViewById(R.id.continueTextView);
        likeBtn = findViewById(R.id.likeBtn);

    }
}
