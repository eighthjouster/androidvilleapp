package com.pachesoft.androidville;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AndroidvilleAPIService {
    @GET("/city")
    Call<AVCity> getAVCity();
}
