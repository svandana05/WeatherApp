package com.android.weatherapp.data.repository

import com.android.weatherapp.data.api.models.WeatherModel
import com.android.weatherapp.data.api.WeatherRemoteDataSource
import com.android.weatherapp.util.Constants
import com.android.weatherapp.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
) : WeatherRepository {

    override suspend fun getWeatherDataWithCityName(cityName: String): Resource<WeatherModel> {
        return try {
            Resource.Success(
                weatherRemoteDataSource.getWeatherDataWithCityName(cityName)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }
}