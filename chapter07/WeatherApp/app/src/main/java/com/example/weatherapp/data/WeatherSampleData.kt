package com.example.weatherapp.data

import com.example.weatherapp.model.WeatherUiModel
import com.example.weatherapp.ui.theme.WeatherType

val sampleData = WeatherUiModel(
    city = cities[2].jp,
    temp = 17,
    maxTemp = 20,
    minTemp = 10,
    iconUrl = "https://openweathermap.org/img/wn/02d@2x.png",
    description = "晴れ",
    humidity = 40,
    wind = 3.6,
    feelsLike = 15,
    sunrise = "06:00",
    sunset = "18:15",
    weatherType = WeatherType.CLEAR
)