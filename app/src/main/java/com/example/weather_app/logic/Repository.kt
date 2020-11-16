package com.example.weather_app.logic

import android.app.DownloadManager
import androidx.lifecycle.liveData
import com.example.weather_app.logic.model.Place
import com.example.weather_app.logic.model.WeatherAppNetwork
import kotlinx.coroutines.Dispatchers


/**
 * 仓库层统一封装入口
 */

object Repository {

    //liveData可以自动构建并返回一个LiveData对象，其可以将异步获取的数据以响应式编程的方式通知给上一层
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        var result = try {
            val placeResponse = WeatherAppNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}