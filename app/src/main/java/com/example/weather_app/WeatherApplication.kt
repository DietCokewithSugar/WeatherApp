package com.example.weather_app;

import android.annotation.SuppressLint;
import android.app.Application
import android.content.Context

/**
 用于获取Context对象
 方法:WeatherApplication.context
 **/
class WeatherApplication : Application() {

    companion object {

        const val TOKEN = "5jObWqR1OCB0ux4I" // 填入你申请到的令牌值

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}