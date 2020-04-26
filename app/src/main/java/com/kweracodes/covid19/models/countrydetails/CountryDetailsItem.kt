package com.kweracodes.covid19.models.countrydetails


import com.google.gson.annotations.SerializedName

data class CountryDetailsItem(
    @SerializedName("Active")
    val active: Int, // 0
    @SerializedName("City")
    val city: String,
    @SerializedName("CityCode")
    val cityCode: String,
    @SerializedName("Confirmed")
    val confirmed: Int, // 56
    @SerializedName("Country")
    val country: String, // Uganda
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Date")
    val date: String, // 2020-04-20T00:00:00Z
    @SerializedName("Deaths")
    val deaths: Int, // 0
    @SerializedName("Lat")
    val lat: String, // 0
    @SerializedName("Lon")
    val lon: String, // 0
    @SerializedName("Province")
    val province: String,
    @SerializedName("Recovered")
    val recovered: Int // 38
)