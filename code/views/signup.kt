package com.example.periodicblissk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.periodicblissk.HomeActivity
import com.example.periodicblissk.R
import com.example.periodicblissk.extensions.Extensions.toast
import com.example.periodicblissk.utils.Firebase_Utils.firebaseAuth
import com.example.periodicblissk.utils.Firebase_Utils.firebaseUser
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<TextInputEditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        createAccountInputsArray = arrayOf( et_email, et_pwd, et_cpwd)

        btn_signin.setOnClickListener {
            signIn()
            startActivity(Intent(this, signin::class.java))
            toast("please sign into your account")
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            toast("Welcome back")
        }
    }

    private fun notEmpty(): Boolean =
            et_email.text.toString().trim().isNotEmpty() &&
            et_pwd.text.toString().trim().isNotEmpty() &&
            et_cpwd.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            et_pwd.text.toString().trim() == et_cpwd.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "$(input.hint) is required"
                }
            }
        } else {
            toast("Passwords don't match !")
        }
        return identical
    }
    private fun signIn(){
        if (identicalPassword()) {
            userEmail = et_email.text.toString().trim()
            userPassword = et_pwd.text.toString().trim()

            //create a user
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        startActivity(Intent(this, signin::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
                    }
                }
        }
    }

}

