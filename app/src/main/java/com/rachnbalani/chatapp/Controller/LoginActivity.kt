package com.rachnbalani.chatapp.Controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rachnbalani.chatapp.R
import com.rachnbalani.chatapp.Services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    fun  loginLoginBtnClicked(view: View){
        val email = loginEmailTxt.text.toString()
        val password = loginPasswordTxt.text.toString()

        AuthService.loginUser(this, email, password) { loginSuccess ->
            if(loginSuccess) {
                AuthService.findUserByEmail(this){ findSuccess ->
                    if(findSuccess){
                        finish()
                    }
                }
            }
        }

    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUsrIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUsrIntent)
        finish()
    }

}