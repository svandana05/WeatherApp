package com.android.weatherapp.ui.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weatherapp.R
import com.android.weatherapp.data.api.models.WeatherModel
import com.android.weatherapp.ui.presentation.component.CircularProgressBar
import com.android.weatherapp.ui.presentation.component.CityWeatherCard
import com.android.weatherapp.ui.presentation.main.ui.theme.Purple80
import com.android.weatherapp.ui.theme.WeatherAppTheme
import com.android.weatherapp.util.AppStrings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchCityViewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        backgroundColor = Color.Transparent
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(it)
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        ) {
                            SearchCityScreenContent(
                                viewModel = searchCityViewModel,
                                searchCityState = searchCityViewModel.searchCityState.collectAsState().value
                            )
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun SearchCityScreenContent(
    viewModel: WeatherViewModel,
    searchCityState: SearchCityState,
) {
    SearchField(viewModel)
    when (searchCityState) {
        is SearchCityState.Loading -> {

        }
        is SearchCityState.Success -> {
            if (searchCityState.weatherModel != null) {
                WantedCityWeatherSection(searchCityState.weatherModel, viewModel)
            }
        }
        is SearchCityState.Error -> {
            SearchResultErrorMessage(searchCityState.errorMessage, viewModel)
        }
    }
}



@Composable
fun SearchField(viewModel: WeatherViewModel) {
    OutlinedTextField(
        value = viewModel.searchFieldValue,
        onValueChange = { viewModel.updateSearchField(it) },
        label = {
            Text(AppStrings.placeholder)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { viewModel.searchCityClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = null
                )
            }
        }
    )
}



@Composable
private fun WantedCityWeatherSection(forecast: WeatherModel, viewModel: WeatherViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(text = AppStrings.subtitle, fontSize = 35.sp, modifier = Modifier.padding(bottom = 8.dp))
        CityWeatherCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .padding(top = 16.dp),
            latitude = forecast.coord!!.lat!!,
            longitude = forecast.coord!!.lon!!,
            city = forecast.name!!,
            country = forecast.sys?.country!!,
            description = forecast.weather[0].description!!,
        )
    }
}

@Composable
private fun SearchResultErrorMessage(errorMessage: String?, viewModel: WeatherViewModel) {
    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}