package net.a6te.lazycoder.muslim_pro_islamicremainders.interfaces;

import net.a6te.lazycoder.muslim_pro_islamicremainders.BuildConfig;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.Athkar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface RetrofitApiCaller {
    @Headers("X-Api-Key: "+ BuildConfig.API_KEY)
    @GET()
    Call<Athkar> getData(@Url String url);
}
