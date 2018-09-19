package com.pachesoft.androidville;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AndroidvilleAPIService {
    @GET("/city")
    Call<AVCity> getAVCity();

    @GET("/houses")
    Call<ArrayList<AVHouse>> getAVHouses();

    @POST("/houses")
    Call<AVHouse> postAVHouse(@Body AVHouse house);
}
