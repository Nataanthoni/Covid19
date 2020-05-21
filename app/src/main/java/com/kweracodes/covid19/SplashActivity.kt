package com.kweracodes.covid19

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        //4second splash time
        Handler().postDelayed({
            //start Main page
            startActivity(Intent(this@SplashActivity, MainActivity ::class.java))
            //finish this activity
            finish()
        },500)

    }
}
