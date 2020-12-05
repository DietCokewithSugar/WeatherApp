package com.example.weather_app.logic.network

import com.example.weather_app.WeatherApplication
import com.example.weather_app.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * 用于访问彩云天气API的Retrofit接口
 */
interface PlaceService {
    //调用searchPlace方法时，会自动发起GET请求来访问api地址
    @GET("v2/place?token=${WeatherApplication.TOKEN}&lang=zh_CN")
    //返回值为Call<PlaceResponse>，Retrofit会将服务器返回的JSON自动解析成PlaceResponse对象
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}