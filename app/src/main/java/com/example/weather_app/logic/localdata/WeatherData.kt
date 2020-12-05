package com.example.weather_app.logic.localdata

import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherData(id : Int, placename : String){

    class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex)

    class Temperature(val max: Float, val min: Float)

    class Skycon(val value: String, val date: Date)

    class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing: List<LifeDescription>, val ultraviolet: List<LifeDescription>, val dressing: List<LifeDescription>)

    class LifeDescription(val desc: String)

}