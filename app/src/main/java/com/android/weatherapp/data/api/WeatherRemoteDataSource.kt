package com.android.weatherapp.data.api

import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeatherDataWithCityName(cityName: String) =
        api.getWeatherData(cityName)
}