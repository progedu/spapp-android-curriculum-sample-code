package com.example.weatherapp.model

import com.example.weatherapp.ui.theme.WeatherType

data class WeatherUiModel(
    val city: String,
    val temp: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val iconUrl: String,
    val description: String,
    val humidity: Int,
    val wind: Double,
    val feelsLike: Int,
    val sunrise: String,
    val sunset: String,
    val weatherType: WeatherType
) {
    val tempText: String
        get() = "${temp}℃"

    val maxMinText: String
        get() = "最高 $maxTemp  最低 $minTemp"

    val humidityText: String
        get() = "${humidity}%"

    val windText: String
        get() = "${wind}m/s"

    val feelsLikeText: String
        get() = "${feelsLike}℃"
}