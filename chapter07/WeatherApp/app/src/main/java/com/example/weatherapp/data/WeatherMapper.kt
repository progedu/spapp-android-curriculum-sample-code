package com.example.weatherapp.data

import com.example.weatherapp.model.WeatherUiModel
import com.example.weatherapp.ui.theme.WeatherType
import com.example.weatherapp.utils.toTimeString
import kotlin.math.roundToInt

fun WeatherResponse.toUiModel(): WeatherUiModel {
    return WeatherUiModel(
        city = name,
        temp = main.temp.roundToInt(),
        maxTemp = main.tempMax.roundToInt(),
        minTemp = main.tempMin.roundToInt(),
        iconUrl = "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png",
        description = weather[0].description,
        humidity = main.humidity,
        wind = wind.speed,
        feelsLike = main.feelsLike.roundToInt(),
        sunrise = sys.sunrise.toTimeString(timezone),
        sunset = sys.sunset.toTimeString(timezone),
        weatherType = weather[0].main.toWeatherType()
    )
}

fun String.toWeatherType(): WeatherType {
    return when (this) {
        "Clear" -> WeatherType.CLEAR
        "Clouds" -> WeatherType.CLOUDS
        "Rain" -> WeatherType.RAIN
        "Snow" -> WeatherType.SNOW
        "Thunderstorm" -> WeatherType.THUNDERSTORM
        else -> WeatherType.CLEAR
    }
}