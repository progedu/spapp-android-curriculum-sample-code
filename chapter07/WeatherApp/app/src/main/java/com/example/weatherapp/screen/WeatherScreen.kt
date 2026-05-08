package com.example.weatherapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.City
import com.example.weatherapp.data.cities
import com.example.weatherapp.data.sampleData
import com.example.weatherapp.model.WeatherUiState
import com.example.weatherapp.model.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.WeatherType

@Composable
fun WeatherRoute(viewModel: WeatherViewModel = viewModel()) {
    WeatherScreen(
        uiState = viewModel.uiState,
        selectedCity = viewModel.selectedCity,
        onCitySelected = viewModel::onCitySelected
    )
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    selectedCity: City,
    onCitySelected: (City) -> Unit
) {
    val weatherType = when (uiState) {
        is WeatherUiState.Success -> uiState.data.weatherType
        WeatherUiState.Loading -> WeatherType.CLOUDS
        WeatherUiState.Error -> WeatherType.THUNDERSTORM
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(weatherType.gradient)
            )
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CityDropdown(
                selectedCity = selectedCity,
                onCitySelected = onCitySelected
            )

            Spacer(Modifier.height(40.dp))

            when(uiState) {
                is WeatherUiState.Success -> CurrentWeather(uiState.data)
                WeatherUiState.Loading -> LoadingView()
                WeatherUiState.Error -> ErrorView()
            }
        }
    }
}

@Preview(name = "成功")
@Composable
private fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreen(
            uiState = WeatherUiState.Success(sampleData),
            selectedCity = cities[2],
            onCitySelected = {}
        )
    }
}

@Preview(name = "取得中")
@Composable
private fun LoadingPreview() {
    WeatherAppTheme {
        WeatherScreen(
            uiState = WeatherUiState.Loading,
            selectedCity = cities[2],
            onCitySelected = {}
        )
    }
}

@Preview(name = "失敗")
@Composable
private fun ErrorPreview() {
    WeatherAppTheme {
        WeatherScreen(
            uiState = WeatherUiState.Error,
            selectedCity = cities[2],
            onCitySelected = {}
        )
    }
}