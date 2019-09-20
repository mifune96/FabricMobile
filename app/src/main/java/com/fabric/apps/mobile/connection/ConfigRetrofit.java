package com.fabric.apps.mobile.connection;

import com.fabric.apps.mobile.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    /**
     *  this function return logging your request & response headers & body
     **/

    private static HttpLoggingInterceptor providerLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    /**
     * this function return sets the default for new connections
     * the default value is 10 seconds
     * */

    private static OkHttpClient provideOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(providerLoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * this function for request & response connections to base url
     * */
    private static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiInterface provideApiService() {
        return provideRetrofit().create(ApiInterface.class);
    }
}
