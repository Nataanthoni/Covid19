package com.kweracodes.covid19.models.allcountries


import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("flag")
    val flag: String, // https://corona.lmao.ninja/assets/img/flags/zw.png
    @SerializedName("_id")
    val id: Int, // 716
    @SerializedName("iso2")
    val iso2: String, // ZW
    @SerializedName("iso3")
    val iso3: String, // ZWE
    @SerializedName("lat")
    val lat: Double, // -20
    @SerializedName("long")
    val long: Double // 30
)