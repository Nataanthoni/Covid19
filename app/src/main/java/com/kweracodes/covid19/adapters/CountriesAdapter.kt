package com.kweracodes.covid19.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kweracodes.covid19.GlobalStatistics
import com.kweracodes.covid19.R
import com.kweracodes.covid19.models.allcountries.Countries
import com.kweracodes.covid19.models.allcountries.CountriesItem

class CountriesAdapter(private val countries: ArrayList<CountriesItem>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.countries_list, parent, false)
        view.setOnClickListener {
            val intent = Intent(parent.context, GlobalStatistics::class.java)
            parent.context.startActivity(intent)
        }
        return ViewHolder(view)

    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.cname.text = countries[position].country
            holder.cases.text = countries[position].cases.toString()
            holder.deaths.text = countries[position].deaths.toString()
            holder.recovered.text = countries[position].recovered.toString()

        }

        class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            val cname : TextView = itemView.findViewById(R.id.country_name)
            val flag : ImageView = itemView.findViewById(R.id.country_flag)
            val cases : TextView = itemView.findViewById(R.id.casesCountries)
            val deaths : TextView = itemView.findViewById(R.id.deathsCountries)
            val recovered : TextView = itemView.findViewById(R.id.recoveredCountries)

        }
}