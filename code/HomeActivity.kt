package com.example.periodicblissk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.periodicblissk.extensions.Extensions.toast
import com.example.periodicblissk.utils.Firebase_Utils.firebaseAuth
import com.example.periodicblissk.view.signin
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.sign


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_signout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, signin::class.java))
            toast("Signed Out")
            finish()
        }

    }
}