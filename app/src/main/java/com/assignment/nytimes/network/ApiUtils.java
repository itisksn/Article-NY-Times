package com.assignment.nytimes.network;


import com.assignment.nytimes.BuildConfig;
import com.assignment.nytimes.screens.articlelist.model.ArticleData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiUtils {

    @GET("viewed/{period}.json?api-key=" + BuildConfig.API_KEY)
    Call<ArticleData> getArticles(@Path("period") int period);

}
