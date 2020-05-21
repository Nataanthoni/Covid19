package com.kweracodes.covid19.models.global


import com.google.gson.annotations.SerializedName

data class Global(
    @SerializedName("active")
    val active: Int, // 1739461
    @SerializedName("affectedCountries")
    val affectedCountries: Int, // 212
    @SerializedName("cases")
    val cases: Int, // 2648317
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Double, // 340
    @SerializedName("critical")
    val critical: Int, // 58202
    @SerializedName("deaths")
    val deaths: Int, // 184614
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double, // 23
    @SerializedName("recovered")
    val recovered: Int, // 724242
    @SerializedName("tests")
    val tests: Int, // 23651789
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Double, // 3036.5
    @SerializedName("todayCases")
    val todayCases: Int, // 12601
    @SerializedName("todayDeaths")
    val todayDeaths: Int, // 548
    @SerializedName("updated")
    val updated: Long // 1587633716944
)