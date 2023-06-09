package com.android.weatherapp.data.api

import com.android.weatherapp.data.api.models.WeatherModel
import com.android.weatherapp.util.NetworkService
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    @GET(NetworkService.FORECAST_END_POINT)
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = NetworkService.API_KEY,
    ): WeatherModel
}