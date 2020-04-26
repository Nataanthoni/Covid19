package com.kweracodes.covid19

import com.kweracodes.covid19.constants.Constants
import com.kweracodes.covid19.models.allcountries.Countries
import com.kweracodes.covid19.models.allcountries.CountriesItem
import com.kweracodes.covid19.models.global.Global
import com.kweracodes.covid19.models.uganda.Uganda
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //Fetches the Api resource data
    @GET(Constants.GLOBAL_URL)
    fun getGlobal():
            Call<Global>

    //Fetches Uganda details

    @GET(Constants.UGANDA_URL)
    fun getUganda(): Call<Uganda>


    //Fetches All countries details

    @GET(Constants.ALL_COUNTRIES_URL)
    fun getAllCountries(): Call <ArrayList<CountriesItem>>
}