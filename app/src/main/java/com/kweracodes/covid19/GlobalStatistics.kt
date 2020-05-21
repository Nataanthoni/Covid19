package com.kweracodes.covid19

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kweracodes.covid19.adapters.CountriesAdapter
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
            .enqueue(object : Callback<ArrayList<CountriesItem>> {
                override fun onFailure(call: Call<ArrayList<CountriesItem>>, t: Throwable) {

                    Toast.makeText(this@GlobalStatistics, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ArrayList<CountriesItem>>,
                    response: Response<ArrayList<CountriesItem>>
                ) {

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

//        Sets actionbar title
//        supportActionBar?.title = "Name here"
//        Removes the default elevation shaddow
//        supportActionBar?.elevation = 0.00F

    }

    //handles the option select menu icon

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
            searchView.setOnCloseListener { true }

            val searchPlate =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            searchPlate.hint = "Search... "
            val searchPlateView: View =
                searchView.findViewById(androidx.appcompat.R.id.search_plate)
            searchPlateView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.transparent
                )
            )

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {// do your logic here                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    //adapter.filter.filter(newText)
                    return false
                }
            })

//            val searchManager =
//                getSystemService(Context.SEARCH_SERVICE) as SearchManager
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        }
        return super.onCreateOptionsMenu(menu)

    }

}
