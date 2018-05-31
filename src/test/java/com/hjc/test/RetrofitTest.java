package com.hjc.test;

import retrofit2.Retrofit;

/**
 * @author : Administrator
 * @date : 2018/5/31 0031 15:31
 * @description : retrofit测试
 */
public class RetrofitTest {

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.sojson.com/open/api/weather/json.shtml?city=北京/").build();
        System.out.println(retrofit);
    }
}
