package com.example.weather_app.logic.model

import com.example.weather_app.logic.network.PlaceService
import com.example.weather_app.logic.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * 统一的网络数据资源访问入口
 * 对所有网络请求的API进行封装
 */

object WeatherAppNetwork {

    //创建PlaceServices接口动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    //定义函数searchPlaces，发起搜索城市数据请求
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call : Call<T>, response: Response<T>){
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable){
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}