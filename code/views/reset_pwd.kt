package com.example.periodicblissk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.periodicblissk.HomeActivity
import com.example.periodicblissk.R
import com.example.periodicblissk.extensions.Extensions.toast
import com.example.periodicblissk.utils.Firebase_Utils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_pwd.*

class reset_pwd : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        mAuth = FirebaseAuth.getInstance()
    }

    fun reset_link(view: android.view.View) {
        val email = et_email.text.toString().trim()
        if(TextUtils.isEmpty(email)){
            toast("Enter you email!")
        }
        mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener{
            task ->
            if (task.isSuccessful){
                toast("Check email to reset you password")
            }
            else{
                toast("Failed to send reset password email!")
            }
        }

    }

    fun next_login(view: android.view.View) {
        startActivity(Intent(this, signin:: class.java))

        finish()
    }


}