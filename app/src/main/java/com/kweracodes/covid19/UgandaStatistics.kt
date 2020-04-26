package com.kweracodes.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kweracodes.covid19.models.global.Global
import kotlinx.android.synthetic.main.activity_uganda_statistics.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UgandaStatistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uganda_statistics)

        //Return details
        RetrofitClient.instance.getGlobal()
            .enqueue(object : Callback<Global> {
                override fun onFailure(call: Call<Global>, t: Throwable) {

                    Toast.makeText(this@UgandaStatistics, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<Global>, response: Response<Global>) {

                    if (response.isSuccessful) {
                        val getUgandaFull: Global = response.body()!!
                        casesFull.text = getUgandaFull.cases.toString()
                        recoveredFull.text = getUgandaFull.recovered.toString()
                        deathsFull.text = getUgandaFull.deaths.toString()

                    } else {
                        SweetAlertDialog(this@UgandaStatistics, SweetAlertDialog.ERROR_TYPE)
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

            SweetAlertDialog(this@UgandaStatistics)
                .setTitleText("Sorry, this section is not yet working.")
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

}
