package com.kweracodes.covid19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_country_statistics.*
import kotlinx.android.synthetic.main.activity_country_statistics.activeFull
import kotlinx.android.synthetic.main.activity_country_statistics.casesFull
import kotlinx.android.synthetic.main.activity_country_statistics.casespermillion
import kotlinx.android.synthetic.main.activity_country_statistics.country_flag
import kotlinx.android.synthetic.main.activity_country_statistics.country_name
import kotlinx.android.synthetic.main.activity_country_statistics.criticalFull
import kotlinx.android.synthetic.main.activity_country_statistics.deathFull
import kotlinx.android.synthetic.main.activity_country_statistics.recoveredFull
import kotlinx.android.synthetic.main.activity_country_statistics.testpermillion
import kotlinx.android.synthetic.main.activity_country_statistics.todayFull
import kotlinx.android.synthetic.main.activity_country_statistics.totaltestsFull
import kotlinx.android.synthetic.main.activity_uganda_statistics.*
import org.jetbrains.anko.appcompat.v7.toolbar


class CountryStatistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_statistics)

        //get the current intent
        val intent = intent
        val cases = intent.getIntExtra("Cases", 1)
        val deaths = intent.getIntExtra("Deaths", 0)
        val recovery = intent.getIntExtra("Recovery", 0)
        val active = intent.getIntExtra("Active", 1)
        val tcases = intent.getIntExtra("Total", 0)
        val testspm = intent.getIntExtra("TestsPM", 0)
        val casespm = intent.getIntExtra("CasesPM", 2)
        val critical = intent.getIntExtra("Critical", 2)
        val country = intent.getStringExtra("CountryN")
        val totalT = intent.getIntExtra("Tests", 1)
        val countryF = intent.getStringExtra("CountryF")

        casesFull.text = cases.toString()
        deathFull.text = deaths.toString()
        recoveredFull.text = recovery.toString()
        activeFull.text = active.toString()
        totaltestsFull.text = totalT.toString()
        testpermillion.text = testspm.toString()
        todayFull.text = tcases.toString()
        testpermillion.text = testspm.toString()
        casespermillion.text = casespm.toString()
        criticalFull.text = critical.toString()
        country_name.text = country

        Glide.with(this@CountryStatistics).load(countryF).into(country_flag)

        //Sets custom actionbar title for each country
        supportActionBar?.title = country

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

}
