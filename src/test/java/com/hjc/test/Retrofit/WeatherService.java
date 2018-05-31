package com.hjc.test.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author : Hjc
 * @date : 2018/5/31 0031 17:30
 */
public interface WeatherService {
    @GET("json.shtml")
    Call<ResponseBody> getWeather(@Query("city")String city);
}
