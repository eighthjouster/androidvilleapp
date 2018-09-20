package com.pachesoft.androidville;

import android.app.Application;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainApp extends Application {
    public MainActivity mainActivity;
    public ServerCommService serverComm;
    private int houseToHighlight = -1;

    @Override
    public void onCreate() {
        super.onCreate();

        serverComm = new ServerCommService();

        getAllHouses();
    }

    public void getAllHouses(int houseToHighlight) {
        this.houseToHighlight = houseToHighlight;
        getAllHouses();
    }
    public void getAllHouses() {
        serverComm.getAllHouses(new Callback<ArrayList<AVHouse>>() {
            @Override
            public void onResponse(Call<ArrayList<AVHouse>> call, Response<ArrayList<AVHouse>> response) {
                ArrayList<AVHouse> houses = response.body();
                mainActivity.getVilleMap().setHouses(houses);
                if (houseToHighlight != -1) {
                    mainActivity.getVilleMap().highlightHouse(houseToHighlight);
                    houseToHighlight = -1;
                }

                for (int i = 0; i < houses.size(); i++) {
                    AVHouse house = houses.get(i);
                    house.selected = false;
                    if (mainActivity.nextHouseId <= house.id) {
                        mainActivity.nextHouseId = house.id + 1;
                    }
                }
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
