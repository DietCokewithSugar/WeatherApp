//package com.example.weather_app.ui.place
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weather_app.R
//import com.example.weather_app.WeatherApplication.Companion.context
//import com.example.weather_app.logic.localdata.WeatherData
//import com.example.weather_app.logic.model.Place
//
//class HistoryAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>){
//
//    inner class ViewHolder(val placeName: TextView)
//
//    override fun getView(position: Int, convertView: View?,parent: ViewGroup): View{
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
//        val holder = ViewHolder(view)
//        if (convertView == null){
//            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
//            val  placeName : TextView = view.findViewById(R.id.placeName1)
//            viewHolder = ViewHolder(placeName)
//            view.tag = viewHolder
//        }else{
//            view = convertView
//            viewHolder = view.tag as ViewHolder
//        }
//
//        val placeName = getItem(position)
//        if (placeName != null){
//            viewHolder.placeName.text = WeatherDate.placename
//        }
//
//        return view
//
//    }
//
//
//}
//
//
//
