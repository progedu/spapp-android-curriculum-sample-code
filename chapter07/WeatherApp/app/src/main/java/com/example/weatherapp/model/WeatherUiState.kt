package com.example.weatherapp.model

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val data: WeatherUiModel) : WeatherUiState
    object Error : WeatherUiState
}