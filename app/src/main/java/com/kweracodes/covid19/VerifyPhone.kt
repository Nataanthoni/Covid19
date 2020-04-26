package com.kweracodes.covid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_verify_phone.*
import java.util.concurrent.TimeUnit
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast


class VerifyPhone : AppCompatActivity() {

    private var verificationId: String = ""
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)

        //puts the back action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button_send_verification.setOnClickListener {

            val phone = phone_number.text.toString().trim()
            if (phone.isEmpty() || phone.length != 9) {
                phone_number.error = "Please enter a valid phone number"
                phone_number.requestFocus()
                return@setOnClickListener

            }

            layoutPhone.visibility = View.GONE
            layoutVerification.visibility = View.VISIBLE

            val phoneNumber = '+' + ccp.selectedCountryCode + phone
            PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(
                    phoneNumber,
                    30,
                    TimeUnit.SECONDS,
                    this,
                    phoneAuthCallbacks
                )

        }

        button_verify.setOnClickListener {
            val code = verification_code.text.toString().trim()

            if (code.isEmpty() || code.length != 6) {
                verification_code.error = "Invalid code"
                verification_code.requestFocus()
                return@setOnClickListener

            }

            verificationId?.let {
                authenticate()

                val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

                signIn(credential)
                progressbar.visibility = View.VISIBLE
            }
        }


    }


    private val phoneAuthCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                phoneAuthCredential.let {
                    signIn(phoneAuthCredential)
                    progressbar.visibility = View.GONE
                }

            }


            override fun onVerificationFailed(exception: FirebaseException) {
                progressbar.visibility = View.GONE

                SweetAlertDialog(this@VerifyPhone, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(exception?.message!!)
                    .show()
                //Toast.makeText(this@VerifyPhone, exception?.message!!,
                //Toast.LENGTH_SHORT).show()

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)

                this@VerifyPhone.verificationId = verificationId
                progressbar.visibility = View.GONE
                toast("Verification code has been sent")
            }


        }

    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    toast("Verification Successful :)")
                    startActivity(Intent(this, MainActivity::class.java))
                    startActivity(intentFor<MainActivity>().newTask().clearTask())
                } else {
                    Toast.makeText(
                        this@VerifyPhone, task.exception?.message!!,
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
    }

    private fun authenticate() {

        val verifiNo = verification_code.text.toString()

        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(verificationId, verifiNo)

        signIn(credential)

    }

    //Let the action bar works
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
