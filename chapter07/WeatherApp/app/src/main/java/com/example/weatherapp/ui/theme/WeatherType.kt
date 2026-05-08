package com.example.weatherapp.ui.theme

import androidx.compose.ui.graphics.Color

enum class WeatherType(val gradient: List<Color>) {
    CLEAR(listOf(Color(0xFF56CCF2), Color(0xFF2F80ED))),
    CLOUDS(listOf(Color(0xFFB0BEC5), Color(0xFF78909C))),
    RAIN(listOf(Color(0xFF5C6BC0), Color(0xFF3949AB))),
    SNOW(listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))),
    THUNDERSTORM(listOf(Color(0xFF616161), Color(0xFF212121)))
}