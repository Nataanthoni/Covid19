package com.kweracodes.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.kweracodes.covid19.models.uganda.Uganda
import kotlinx.android.synthetic.main.activity_uganda_statistics.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UgandaStatistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uganda_statistics)

        //Return details
        RetrofitClient.instance.getUganda()
            .enqueue(object : Callback<Uganda> {
                override fun onFailure(call: Call<Uganda>, t: Throwable) {

                    Toast.makeText(this@UgandaStatistics, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<Uganda>, response: Response<Uganda>) {

                    if (response.isSuccessful) {
                        val getUgandaFull: Uganda = response.body()!!
                        casesFull.text = getUgandaFull.cases.toString()
                        recoveredFull.text = getUgandaFull.recovered.toString()
                        deathFull.text = getUgandaFull.deaths.toString()

                        totaltestsFull.text = getUgandaFull.tests.toString()
                        todayFull.text = getUgandaFull.todayCases.toString()
                        testpermillion.text = getUgandaFull.testsPerOneMillion.toString()
                        casespermillion.text = getUgandaFull.casesPerOneMillion.toString()
                        criticalFull.text = getUgandaFull.critical.toString()
                        activeFull.text = getUgandaFull.active.toString()

                        Glide.with(this@UgandaStatistics).load(getUgandaFull.countryInfo.flag).into(country_flag)

                    } else {
                        SweetAlertDialog(this@UgandaStatistics, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Response Error")
                            .show()
                    }
                }

            })

    }

}
