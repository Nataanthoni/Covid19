package com.kweracodes.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kweracodes.covid19.adapters.CountriesAdapter
import com.kweracodes.covid19.models.allcountries.Countries
import com.kweracodes.covid19.models.allcountries.CountriesItem
import com.kweracodes.covid19.models.global.Global
import kotlinx.android.synthetic.main.activity_global_statistics.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalStatistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_statistics)

        //Return details
        RetrofitClient.instance.getGlobal()
            .enqueue(object : Callback<Global> {
                override fun onFailure(call: Call<Global>, t: Throwable) {

                    Toast.makeText(this@GlobalStatistics, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<Global>, response: Response<Global>) {

                    if (response.isSuccessful) {
                        var getUganda: Global = response.body()!!
                        confirmedCases.text = getUganda.cases.toString()
                        recoveredCases.text = getUganda.recovered.toString()
                        fatalCases.text = getUganda.deaths.toString()

                    } else {
                        SweetAlertDialog(this@GlobalStatistics, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Response Error")
                            .show()
                    }
                }

            })

        //Return all countries details
        RetrofitClient.instance.getAllCountries()
            .enqueue(object : Callback <ArrayList<CountriesItem>> {
                override fun onFailure(call: Call<ArrayList<CountriesItem>>, t: Throwable) {

                    Toast.makeText(this@GlobalStatistics, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<ArrayList<CountriesItem>>, response: Response<ArrayList<CountriesItem>>) {

                    if (response.isSuccessful) {

                        val getCountries: ArrayList<CountriesItem> = response.body()!!
                        countriesRecycler.adapter = CountriesAdapter(getCountries)
                        countriesRecycler.layoutManager = LinearLayoutManager(this@GlobalStatistics)

                        //Progress bar disappears after loading view
                        countriesprogressbar.visibility = View.GONE

                    } else {
                        SweetAlertDialog(this@GlobalStatistics, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Response Error")
                            .show()
                    }
                }

            })

    }

    //handles the option select menu icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.select_option_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == R.id.action_sort) {

            SweetAlertDialog(this@GlobalStatistics)
                .setTitleText("Sorry, this section is not yet working.")
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

}
