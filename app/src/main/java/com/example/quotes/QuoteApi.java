package com.example.quotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi {

    @GET("api/v1/quote")
    Call<BaseApi> getQuote();

}
