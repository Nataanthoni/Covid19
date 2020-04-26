package com.kweracodes.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.kweracodes.covid19.models.uganda.Uganda
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Open Global Statistics Activity
        seeGlobalStat.setOnClickListener {
            val intent = Intent(this, GlobalStatistics:: class.java)
            startActivity(intent)
        }

        //Open Global Statistics Activity
        confirmedCasess.setOnClickListener {
            val intent = Intent(this, UgandaStatistics:: class.java)
            startActivity(intent)
        }
        //Return details
        RetrofitClient.instance.getUganda()
            .enqueue(object : Callback<Uganda> {
                override fun onFailure(call: Call<Uganda>, t: Throwable) {

                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<Uganda>, response: Response<Uganda>) {

                    if (response.isSuccessful) {
                        var getDetails: Uganda = response.body()!!
                        confirmedCases.text = getDetails.cases.toString()
                        recoveredCases.text = getDetails.recovered.toString()
                        fatalCases.text = getDetails.deaths.toString()

                    } else {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.ERROR_TYPE)
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

            SweetAlertDialog(this@MainActivity)
                .setTitleText("Sorry, this section is not yet working.")
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            // User is signed in
            val intent = Intent(applicationContext, Welcome::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }


    }


}
