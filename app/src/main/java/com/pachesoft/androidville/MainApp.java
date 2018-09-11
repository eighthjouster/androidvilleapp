package com.pachesoft.androidville;

import android.app.Application;
import android.os.StrictMode;

import java.util.ArrayList;

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

        Call<ArrayList<AVHouse>> callAsync = service.getAVHouses();

        callAsync.enqueue(new Callback<ArrayList<AVHouse>>() {
            @Override
            public void onResponse(Call<ArrayList<AVHouse>> call, Response<ArrayList<AVHouse>> response) {
                ArrayList<AVHouse> houses = response.body();
                System.out.println("===================== GOT A RESPONSE!!!! 1");
                System.out.println(houses.get(0).address.y);
            }

            @Override
            public void onFailure(Call<ArrayList<AVHouse>> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }
}
