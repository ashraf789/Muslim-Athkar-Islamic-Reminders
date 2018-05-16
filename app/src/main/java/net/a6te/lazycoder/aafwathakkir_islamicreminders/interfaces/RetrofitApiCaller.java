package net.a6te.lazycoder.aafwathakkir_islamicreminders.interfaces;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.BuildConfig;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Athkar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface RetrofitApiCaller {
    @Headers("X-Api-Key: "+ BuildConfig.API_KEY)
    @GET()
    Call<Athkar> getData(@Url String url);
}
