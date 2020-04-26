package com.kweracodes.covid19.models.uganda


import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("flag")
    val flag: String, // https://corona.lmao.ninja/assets/img/flags/ug.png
    @SerializedName("_id")
    val id: Int, // 800
    @SerializedName("iso2")
    val iso2: String, // UG
    @SerializedName("iso3")
    val iso3: String, // UGA
    @SerializedName("lat")
    val lat: Int, // 1
    @SerializedName("long")
    val long: Int // 32
)