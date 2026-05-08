package com.example.weatherapp.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.City
import com.example.weatherapp.data.cities
import com.example.weatherapp.data.toUiModel
import com.example.weatherapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var uiState by mutableStateOf<WeatherUiState>(WeatherUiState.Loading)
        private set
    var selectedCity by mutableStateOf(cities[2])
        private set

    init {
        fetchWeather(selectedCity.en)
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            try {
                // ① 地名 → 緯度・経度を取得
                val location = RetrofitInstance.api.getLocation(
                    city = city,
                    apiKey = BuildConfig.WEATHER_API_KEY
                ).firstOrNull()

                // 取得できなかったらError
                if (location == null) {
                    uiState = WeatherUiState.Error
                    return@launch
                }

                // ② 緯度・経度 → 天気を取得
                val response = RetrofitInstance.api.getWeather(
                    lat = location.lat,
                    lon = location.lon,
                    apiKey = BuildConfig.WEATHER_API_KEY
                )

                // ③ UI用に変換
                uiState = WeatherUiState.Success(
                    response.toUiModel()
                )
            } catch (e: Exception) {
                uiState = WeatherUiState.Error
                Log.e("WEATHER_API_ERROR", e.toString())
            }
        }
    }

    fun onCitySelected(city: City) {
        selectedCity = city
        fetchWeather(city.en)
    }
}