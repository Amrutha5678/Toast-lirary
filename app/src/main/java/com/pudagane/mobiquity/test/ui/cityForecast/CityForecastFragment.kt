package com.pudagane.mobiquity.test.ui.cityForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pudagane.mobiquity.test.R
import com.pudagane.mobiquity.test.adapter.WeatherForecastAdapter

class CityForecastFragment : Fragment() {

    private lateinit var forecastViewModel: ForecastViewModel

    private val forecastAdapter = WeatherForecastAdapter(arrayListOf())
    lateinit var recyclerviewWeatherForecast:RecyclerView
    lateinit var list_error:TextView
    lateinit var loading_view:ProgressBar

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_city_forecast, container, false)
        val cityName = requireArguments().getString("CITY_NAME")
        recyclerviewWeatherForecast=root.findViewById(R.id.recyclerviewWeatherForecast)
        list_error=root.findViewById(R.id.list_error)
        loading_view=root.findViewById(R.id.loading_view)

        forecastViewModel.refresh(cityName!!)

        observeViewModel()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerviewWeatherForecast.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
            adapter = forecastAdapter
        }
    }

    private fun observeViewModel() {
        forecastViewModel.weatherForcastList.observe(viewLifecycleOwner, Observer { forecastData->
            forecastData?.let {
                recyclerviewWeatherForecast.visibility = View.VISIBLE
                forecastAdapter.updateData(it)
            }
        })

        forecastViewModel.dataLoadError.observe(viewLifecycleOwner, Observer { isError->
            isError?.let { list_error.visibility=if(it) View.VISIBLE else View.GONE }
        })

        forecastViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    list_error.visibility=View.GONE
                    recyclerviewWeatherForecast.visibility=View.GONE
                }}

        })
    }
}