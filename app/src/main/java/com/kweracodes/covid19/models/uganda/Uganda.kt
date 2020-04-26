package com.kweracodes.covid19.models.uganda


import com.google.gson.annotations.SerializedName

data class Uganda(
    @SerializedName("active")
    val active: Int, // 17
    @SerializedName("cases")
    val cases: Int, // 63
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Int, // 1
    @SerializedName("continent")
    val continent: String, // Africa
    @SerializedName("country")
    val country: String, // Uganda
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("critical")
    val critical: Int, // 0
    @SerializedName("deaths")
    val deaths: Int, // 0
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Int, // 0
    @SerializedName("recovered")
    val recovered: Int, // 46
    @SerializedName("tests")
    val tests: Int, // 16057
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Int, // 351
    @SerializedName("todayCases")
    val todayCases: Int, // 0
    @SerializedName("todayDeaths")
    val todayDeaths: Int, // 0
    @SerializedName("updated")
    val updated: Long // 1587630116334
)