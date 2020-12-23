package com.example.weather_app.logic.model
import com.google.gson.annotations.SerializedName

//获取实时天气接口所返回的JSON数据格式的数据模型
class RealtimeResponse(val status: String, val result: Result) {

    class Result(val realtime: Realtime)

    class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)

    class AirQuality(val aqi: AQI)

    class AQI(val chn: Float)

}