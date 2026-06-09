package com.example.weatherapp.data

data class City(
    val en: String,
    val jp: String
)

val cities = listOf(
    City("Sapporo", "北海道"),
    City("Sendai", "仙台"),
    City("Shinjuku", "東京"),
    City("Nagoya", "名古屋"),
    City("Osaka-shi", "大阪"),
    City("Kure", "広島"),
    City("Kitakyushu", "福岡"),
    City("Naha", "那覇"),
    City("London", "ロンドン"),
    City("Paris", "パリ"),
    City("New York", "ニューヨーク")
)
