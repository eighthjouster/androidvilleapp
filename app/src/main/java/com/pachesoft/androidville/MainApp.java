package com.pachesoft.androidville;

import android.app.Application;
import android.os.StrictMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3010")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AndroidvilleAPIService service = retrofit.create(AndroidvilleAPIService.class);

        Call<AVCity> callAsync = service.getAVCity();

        callAsync.enqueue(new Callback<AVCity>() {
            @Override
            public void onResponse(Call<AVCity> call, Response<AVCity> response) {
                AVCity city = response.body();
                System.out.println("===================== GOT A RESPONSE!!!! 1");
                System.out.println(city.name);
            }

            @Override
            public void onFailure(Call<AVCity> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }
}
