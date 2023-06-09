package com.android.weatherapp.data.repository

import javax.inject.Inject

class GetWeatherWithCityNameUseCase @Inject constructor(private val weatherRepositoryImpl: WeatherRepositoryImpl) {
    suspend fun getWeather(cityName: String) =
        weatherRepositoryImpl.getWeatherDataWithCityName(cityName)
}