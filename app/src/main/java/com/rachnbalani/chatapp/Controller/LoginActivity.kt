package com.rachnbalani.chatapp.Controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rachnbalani.chatapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    fun  loginLoginBtnClicked(view: View){

    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUsrIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUsrIntent)

    }

}