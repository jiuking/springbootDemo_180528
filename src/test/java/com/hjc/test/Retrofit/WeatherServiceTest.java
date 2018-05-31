package com.hjc.test.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author : Administrator
 * @date : 2018/5/31 0031 17:33
 * @description : Retrofit天气接口实现
 */
public class WeatherServiceTest {
    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.sojson.com/open/api/weather/")
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<ResponseBody> call = service.getWeather("北京");
        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
