package com.android.weatherapp.ui.presentation.main

import com.android.weatherapp.data.api.models.WeatherModel


sealed interface SearchCityState {
    data class Success(val weatherModel: WeatherModel?): SearchCityState
    data class Error(val errorMessage: String?): SearchCityState

    object Loading: SearchCityState
}