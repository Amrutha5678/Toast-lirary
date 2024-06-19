package com.pudagane.mobiquity.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pudagane.mobiquity.test.R
import com.pudagane.mobiquity.test.util.getProgressDrawable
import com.pudagane.mobiquity.test.util.loadImage
import com.pudagane.weatherforecast.model.Data

class WeatherForecastAdapter(private var weatherForcast: ArrayList<Data>): RecyclerView.Adapter<WeatherForecastAdapter.WeatherForcastViewHolder>(){

    fun updateData(newWeatherForcast: ArrayList<Data>){
        weatherForcast.clear()
        weatherForcast.addAll(newWeatherForcast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= WeatherForcastViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item,parent,false)
    )

    override fun getItemCount()=weatherForcast.size

    override fun onBindViewHolder(holder: WeatherForcastViewHolder, position: Int) {
        holder.bind(weatherForcast[position])
    }


    class WeatherForcastViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val date = view.findViewById<TextView>(R.id.tvDate)
        private val maxTemp = view.findViewById<TextView>(R.id.maxTemp)
        private val minTemp = view.findViewById<TextView>(R.id.minTemp)
        private val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(data:Data){
            date.text = data.validDate
            maxTemp.text=data.maxTemp.toString()
            minTemp.text = data.minTemp.toString()
            ivLogo.loadImage("https://www.weatherbit.io/static/img/icons/"+data.weather.icon+".png",progressDrawable)
        }
    }
}