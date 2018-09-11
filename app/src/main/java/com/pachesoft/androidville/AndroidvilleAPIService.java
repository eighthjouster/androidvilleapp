package com.pachesoft.androidville;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AndroidvilleAPIService {
    @GET("/city")
    Call<AVCity> getAVCity();

    @GET("/houses")
    Call<ArrayList<AVHouse>> getAVHouses();
}
