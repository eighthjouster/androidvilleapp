package com.pachesoft.androidville;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerCommService
{
    AndroidvilleAPIService service;

    ServerCommService() {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.0.2.2:3010") // Emulator's host machine (localhost parent.)
                .baseUrl("http://androidville.rppalencia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AndroidvilleAPIService.class);
    }

    public void getAllHouses(Callback<ArrayList<AVHouse>> responseHandler) {
        Call<ArrayList<AVHouse>> callAsync = service.getAVHouses();
        callAsync.enqueue(responseHandler);
    }

    public void addHouse(AVHouse house, Callback<AVHouse> responseHandler) {
        Call<AVHouse> callAsync = service.postAVHouse(house);
        callAsync.enqueue(responseHandler);
    }

    public void updateHouse(AVHouse house, Callback<AVHouse> responseHandler) {
        Call<AVHouse> callAsync = service.putAVHouse(house.id, house);
        callAsync.enqueue(responseHandler);
    }

    public void deleteHouse(AVHouse house, Callback<AVHouse> responseHandler) {
        Call<AVHouse> callAsync = service.deleteAVHouse(house.id);
        callAsync.enqueue(responseHandler);
    }
}
