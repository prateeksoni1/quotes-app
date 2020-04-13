package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pro-quotes-backend.herokuapp.com/").build();

        QuoteApi quoteApi = retrofit.create(QuoteApi.class);

        Call<Quote> quote = quoteApi.getQuote();

        quote.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (!response.isSuccessful()) {
                    Log.i("Response", response.code() + response.message());
                }
                if (response.body() != null) {
                    Log.i("Response", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.e("Error", "onFailure: " + t.getMessage());
            }
        });

    }
}
