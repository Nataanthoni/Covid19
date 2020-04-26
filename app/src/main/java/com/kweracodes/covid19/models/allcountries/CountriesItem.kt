package com.kweracodes.covid19.models.allcountries


import com.google.gson.annotations.SerializedName

data class CountriesItem(
    @SerializedName("active")
    val active: Int, // 22
    @SerializedName("cases")
    val cases: Int, // 28
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Int, // 2
    @SerializedName("continent")
    val continent: String, // Africa
    @SerializedName("country")
    val country: String, // Zimbabwe
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("critical")
    val critical: Int, // 0
    @SerializedName("deaths")
    val deaths: Int, // 4
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Int, // 0
    @SerializedName("recovered")
    val recovered: Int, // 2
    @SerializedName("tests")
    val tests: Int, // 4990
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Int, // 336
    @SerializedName("todayCases")
    val todayCases: Int, // 0
    @SerializedName("todayDeaths")
    val todayDeaths: Int, // 0
    @SerializedName("updated")
    val updated: Long // 1587631916612
)