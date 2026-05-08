package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class GeoResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String
) {
    data class LocalNames(
        @SerializedName("ar")
        val ar: String,
        @SerializedName("be")
        val be: String,
        @SerializedName("bg")
        val bg: String,
        @SerializedName("ca")
        val ca: String,
        @SerializedName("cs")
        val cs: String,
        @SerializedName("cy")
        val cy: String,
        @SerializedName("da")
        val da: String,
        @SerializedName("de")
        val de: String,
        @SerializedName("el")
        val el: String,
        @SerializedName("en")
        val en: String,
        @SerializedName("eo")
        val eo: String,
        @SerializedName("es")
        val es: String,
        @SerializedName("et")
        val et: String,
        @SerializedName("fa")
        val fa: String,
        @SerializedName("fi")
        val fi: String,
        @SerializedName("fr")
        val fr: String,
        @SerializedName("he")
        val he: String,
        @SerializedName("hr")
        val hr: String,
        @SerializedName("hu")
        val hu: String,
        @SerializedName("ia")
        val ia: String,
        @SerializedName("io")
        val io: String,
        @SerializedName("is")
        val isX: String,
        @SerializedName("it")
        val it: String,
        @SerializedName("ja")
        val ja: String,
        @SerializedName("kn")
        val kn: String,
        @SerializedName("ko")
        val ko: String,
        @SerializedName("ku")
        val ku: String,
        @SerializedName("la")
        val la: String,
        @SerializedName("lb")
        val lb: String,
        @SerializedName("lt")
        val lt: String,
        @SerializedName("lv")
        val lv: String,
        @SerializedName("mi")
        val mi: String,
        @SerializedName("mr")
        val mr: String,
        @SerializedName("nl")
        val nl: String,
        @SerializedName("oc")
        val oc: String,
        @SerializedName("pl")
        val pl: String,
        @SerializedName("pt")
        val pt: String,
        @SerializedName("ru")
        val ru: String,
        @SerializedName("sk")
        val sk: String,
        @SerializedName("sl")
        val sl: String,
        @SerializedName("sr")
        val sr: String,
        @SerializedName("sv")
        val sv: String,
        @SerializedName("ta")
        val ta: String,
        @SerializedName("tg")
        val tg: String,
        @SerializedName("th")
        val th: String,
        @SerializedName("tr")
        val tr: String,
        @SerializedName("uk")
        val uk: String,
        @SerializedName("vi")
        val vi: String,
        @SerializedName("zh")
        val zh: String
    )
}