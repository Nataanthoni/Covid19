package com.kweracodes.covid19.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kweracodes.covid19.CountryStatistics
import com.kweracodes.covid19.R
import com.kweracodes.covid19.models.allcountries.CountriesItem
import java.util.*
import kotlin.collections.ArrayList

class CountriesAdapter(private val countries : ArrayList<CountriesItem>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>(), Filterable {
    //Initialize default filter result
    var countryFilterList = ArrayList<CountriesItem>()

    init {
        countryFilterList = countries
    }
    //End of filter intialization

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cname.text = countries[position].country
        holder.cases.text = countries[position].cases.toString()
        holder.deaths.text = countries[position].deaths.toString()
        holder.recovered.text = countries[position].recovered.toString()
        Glide.with(holder.flag).load(countries[position].countryInfo.flag).centerCrop()
            .into(holder.flag)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.countries_list, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, CountryStatistics::class.java)

            //Pass data via intent
            val cases = countries[holder.adapterPosition].cases
            val deaths = countries[holder.adapterPosition].deaths
            val recovery = countries[holder.adapterPosition].recovered
            val tests = countries[holder.adapterPosition].tests
            val critical = countries[holder.adapterPosition].critical
            val tcases = countries[holder.adapterPosition].todayCases
            val tdeaths = countries[holder.adapterPosition].todayDeaths
            val testspm = countries[holder.adapterPosition].testsPerOneMillion
            val active = countries[holder.adapterPosition].active
            val casespm = countries[holder.adapterPosition].casesPerOneMillion
            val countryname = countries[holder.adapterPosition].country
            val countryflag = countries[holder.adapterPosition].countryInfo.flag

            intent.putExtra("Cases", cases)
            intent.putExtra("Deaths", deaths)
            intent.putExtra("Recovery", recovery)
            intent.putExtra("Critical", critical)
            intent.putExtra("Tests", tests)
            intent.putExtra("Total", tcases)
            intent.putExtra("TotalDeath", tdeaths)
            intent.putExtra("TestsPM", testspm)
            intent.putExtra("Active", active)
            intent.putExtra("CasesPM", casespm)
            intent.putExtra("CountryN", countryname)
            intent.putExtra("CountryF", countryflag)

            parent.context.startActivity(intent)
        }
        //Changed from return ViewHolder(view)
        return holder

    }

    override fun getItemCount() = countries.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cname: TextView = itemView.findViewById(R.id.country_name)
        val flag: ImageView = itemView.findViewById(R.id.country_flag)
        val cases: TextView = itemView.findViewById(R.id.casesCountries)
        val deaths: TextView = itemView.findViewById(R.id.deathsCountries)
        val recovered: TextView = itemView.findViewById(R.id.recoveredCountries)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                countryFilterList = if (charSearch.isEmpty()) {
                    countries
                } else {
                    val resultList = ArrayList<CountriesItem>()
                    for (row in countries) {
                        if (row.country.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<CountriesItem>
                notifyDataSetChanged()
            }

        }
    }//End of the getFilter function

}