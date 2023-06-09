package com.android.weatherapp.ui.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weatherapp.ui.theme.Purple500

@Composable
fun CityWeatherCard(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
) {
    Card(
        modifier = modifier,
        backgroundColor = Purple500,
        shape = MaterialTheme.shapes.medium
    ) {
        WeatherInfo(latitude, longitude, city, country, description)
    }
}


@Composable
private fun WeatherInfo(
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LocationAndDescription(latitude, longitude, city, country, description)
    }
}


@Composable
private fun LocationAndDescription(
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Text(text = description, fontSize = 30.sp, modifier = Modifier.padding(bottom = 8.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = "Lat:${latitude}  Lon:${longitude}")
            Text(text = "${city}, $country")
        }

    }
}
