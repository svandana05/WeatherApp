package com.android.weatherapp.data.api.models

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") var all : Int? = null
)
