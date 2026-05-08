package com.example.weatherapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.data.sampleData
import com.example.weatherapp.model.WeatherUiModel

@Composable
fun CurrentWeather(weatherData: WeatherUiModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = weatherData.city,
            fontSize = 26.sp,
            color = Color.White
        )

        Text(
            text = weatherData.tempText,
            fontSize = 68.sp,
            fontWeight = FontWeight.Light,
            color = Color.White
        )

        Text(
            text = weatherData.maxMinText,
            fontSize = 18.sp,
            color = Color.White
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = weatherData.iconUrl,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = weatherData.description,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.2f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row {
                    WeatherInfo("湿度", weatherData.humidityText, Modifier.weight(1f))
                    WeatherInfo("風速", weatherData.windText, Modifier.weight(1f))
                    WeatherInfo("体感", weatherData.feelsLikeText, Modifier.weight(1f))
                }
                Row {
                    WeatherInfo("日の出", weatherData.sunrise, Modifier.weight(1f))
                    WeatherInfo("日の入", weatherData.sunset, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun WeatherInfo(
    label: String,
    value: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 14.sp
        )
        Text(
            text = value,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
private fun CurrentWeatherPreview() {
    CurrentWeather(sampleData)
}