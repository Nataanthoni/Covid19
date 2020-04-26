package com.kweracodes.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_welcome)

        joinwithphone.setOnClickListener {
            val intent = Intent(this, VerifyPhone::class.java)

            // start your next activity
            startActivity(intent)
        }
    }


}
