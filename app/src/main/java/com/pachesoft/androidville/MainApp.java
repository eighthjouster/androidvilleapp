package com.pachesoft.androidville;

import android.app.Activity;
import android.app.Application;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.StrictMode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApp extends Application {
    public MainActivity mainActivity;
    public ServerCommService serverComm;

    @Override
    public void onCreate() {
        super.onCreate();

        serverComm = new ServerCommService();

        getAllHouses();
    }

    public void getAllHouses() {
        serverComm.getAllHouses(new Callback<ArrayList<AVHouse>>() {
            @Override
            public void onResponse(Call<ArrayList<AVHouse>> call, Response<ArrayList<AVHouse>> response) {
                ArrayList<AVHouse> houses = response.body();
                mainActivity.getVilleMap().setHouses(houses);
            }

            @Override
            public void onFailure(Call<ArrayList<AVHouse>> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.getVilleMap().setMainApp(this);
        this.mainActivity.vScroll.setMainActivity(mainActivity);
    }
}
