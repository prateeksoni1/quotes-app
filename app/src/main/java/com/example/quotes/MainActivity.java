package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView, authorTextView, likesTextView;
    private ImageView likeBtn;
    private Retrofit retrofit;

    void setRandomQuote(Quote quote) {
        quoteTextView.setText(quote.getText());
        authorTextView.setText(quote.getAuthor());
        likesTextView.setText(String.format("%s likes", quote.getLikes().toString()));
    }

    void getRandomQuote() {
        QuoteApi quoteApi = retrofit.create(QuoteApi.class);

        Call<BaseApi> baseApiCall = quoteApi.getQuote();

        baseApiCall.enqueue(new Callback<BaseApi>() {
            @Override
            public void onResponse(Call<BaseApi> call, Response<BaseApi> response) {
                if (!response.isSuccessful()) {
                    Log.i("Response", response.code() + response.message());
                }
                if (response.body() != null) {
                    BaseApi result = response.body();
                    Log.i("response", "onResponse: " + result.data.quote.getText());
                    setRandomQuote(result.data.quote);
                }
            }

            @Override
            public void onFailure(Call<BaseApi> call, Throwable t) {
                Log.e("Error", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        likesTextView = findViewById(R.id.likesTextView);
        likeBtn = findViewById(R.id.likeBtn);

        retrofit = new Retrofit.Builder().baseUrl("https://pro-quotes-backend.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

        getRandomQuote();

    }

    public void onContinue(View view) {
        getRandomQuote();
    }
}
