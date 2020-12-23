package com.example.weather_app.ui.place

import androidx.lifecycle.*
import com.example.weather_app.logic.Repository
import com.example.weather_app.logic.model.Place

/**
 * 链接逻辑层与ui界面
 * 旋转时数据不会丢失
 */

class PlaceViewModel : ViewModel(){
    /**
     * 将传入的搜索的搜索参数赋值
     */
    private val searchLiveData = MutableLiveData<String>()

    //对界面上的显示城市的数据进行缓存,放在ViewModel类时,手机旋转不会丢失
    val placeList = ArrayList<Place>()

    //使用switchMap观察这个对象，使用searchPlace()时,会自动转换为switchMap方法
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place,count: Int) = Repository.savePlace(place,count)

    fun getSavedPlace(count: Int) = Repository.getSavedPlace(count)

    fun isPlaceSaved(count: Int) = Repository.isPlaceSaved(count)

}