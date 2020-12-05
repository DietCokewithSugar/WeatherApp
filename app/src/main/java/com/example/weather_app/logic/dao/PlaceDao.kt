package com.example.weather_app.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.weather_app.WeatherApplication
import com.example.weather_app.logic.model.Place
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place: Place,count : Int) {
        sharedPreferences().edit {
            putString("place$count", Gson().toJson(place))
        }
    }

    fun getSavedPlace(count: Int): Place {
        val placeJson = sharedPreferences().getString("place$count", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved(count: Int) = sharedPreferences().contains("place$count")

    private fun sharedPreferences() =
        WeatherApplication.context.getSharedPreferences("WeatherApp", Context.MODE_PRIVATE)


}