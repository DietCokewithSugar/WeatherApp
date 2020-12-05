package com.example.weather_app.logic

import androidx.lifecycle.liveData
import com.example.weather_app.logic.dao.PlaceDao
import com.example.weather_app.logic.model.Place
import com.example.weather_app.logic.model.Weather
import com.example.weather_app.logic.network.WeatherAppNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherAppNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String, placeName: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                WeatherAppNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                WeatherAppNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    fun savePlace(place: Place,count: Int) = PlaceDao.savePlace(place,count)

    fun getSavedPlace(count: Int) = PlaceDao.getSavedPlace(count)

    fun isPlaceSaved(count: Int) = PlaceDao.isPlaceSaved(count)

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}