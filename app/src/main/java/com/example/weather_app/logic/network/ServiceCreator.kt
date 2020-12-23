package com.example.weather_app.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit构建器
 */
object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/" //指定根路径

    private val retrofit = Retrofit.Builder()//构建Retrofit动态代理对象
        .baseUrl(BASE_URL)                   //构建完成后可调用接口的所有方法
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)  //封装create方法，创建相应Service接口的动态代理对象

    inline fun <reified T> create(): T = create(T::class.java)
    //inline修饰方法 reified 修饰范型，最终可以用ServiceCreator.create()来获取retrofit接口的动态代理对象
}