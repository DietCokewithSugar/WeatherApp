package com.example.weather_app.logic.model

import com.google.gson.annotations.SerializedName


//用于定义城市数据接口返回的JSON的格式的数据

data class PlaceResponse(val status: String, val places:List<Place>)

data class Place(val name:String, val location: Location,
                 @SerializedName("formatted_address") val address: String)
                //@SerializedName 用于建立Json与Kotlin字段的映射关系

data class Location(val lng: String, val lat: String)