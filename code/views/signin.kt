package com.example.periodicblissk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.periodicblissk.HomeActivity
import com.example.periodicblissk.R
import com.example.periodicblissk.extensions.Extensions.toast
import com.example.periodicblissk.utils.Firebase_Utils.firebaseAuth
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_signin.*

class signin : AppCompatActivity() {
    lateinit var signin_Email: String
    lateinit var signin_Password: String
    lateinit var signin_InputsArray: Array<TextInputEditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signin_InputsArray = arrayOf(et_signinemail, et_signinpwd)
        btn_signin.setOnClickListener{
            signInUser()
        }
    }
    private fun notEmpty(): Boolean = signin_Email.isNotEmpty() && signin_Password.isNotEmpty()

    private fun signInUser(){
        signin_Email = et_signinemail.text.toString().trim()
        signin_Password = et_signinpwd.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signin_Email,signin_Password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this,HomeActivity:: class.java))
                        toast("Signed In Successfully")
                        finish()
                    } else {
                        toast("Sign In Failed")
                    }
                }
        } else {
            signin_InputsArray.forEach { input ->
                if(input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        }
    }

    fun nextpage(view: android.view.View) {
        startActivity(Intent(this, signup::class.java))
        finish()
    }

    fun next_page_reset(view: android.view.View) {
        startActivity(Intent(this, reset_pwd::class.java))
        finish()
    }
}