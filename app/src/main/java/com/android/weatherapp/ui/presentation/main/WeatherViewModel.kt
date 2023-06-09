package com.android.weatherapp.ui.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.data.repository.GetWeatherWithCityNameUseCase
import com.android.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherWithCityName: GetWeatherWithCityNameUseCase,
) : ViewModel()  {

    private val _searchCityState = MutableStateFlow<SearchCityState>(SearchCityState.Loading)
    val searchCityState = _searchCityState.asStateFlow()

    var searchFieldValue by mutableStateOf("")
        private set

    private fun checkSearchFieldValue(): Boolean {
        return searchFieldValue.isNotEmpty()
    }

    var searchIconClick by mutableStateOf(Boolean)
        private set


    fun updateSearchField(input: String) {
        searchFieldValue = input
    }

    fun searchCityClick() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (checkSearchFieldValue()) {
                    fetchWeatherWithCityName(searchFieldValue)
                } else {
                    _searchCityState.value = SearchCityState.Error("Error")
                }
            } catch (e: Exception) {
                _searchCityState.value = SearchCityState.Error(e.message)
            }
        }
    }

    private suspend fun fetchWeatherWithCityName(cityName: String) {
        when (val result = getWeatherWithCityName.getWeather(cityName)) {
            is Resource.Success -> {
                _searchCityState.value = SearchCityState.Success(result.data)
            }
            is Resource.Error -> {
                _searchCityState.value = SearchCityState.Error(result.message)
            }
        }
    }

}