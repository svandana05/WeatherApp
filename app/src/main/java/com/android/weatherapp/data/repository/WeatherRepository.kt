package com.android.weatherapp.data.repository

import com.android.weatherapp.data.api.models.WeatherModel
import com.android.weatherapp.util.Resource

interface WeatherRepository {
    suspend fun getWeatherDataWithCityName(cityName: String): Resource<WeatherModel>
}