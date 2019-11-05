package com.rachnbalani.chatapp.Controller

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.rachnbalani.chatapp.R
import com.rachnbalani.chatapp.Services.AuthService
import com.rachnbalani.chatapp.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlin.random.Random

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profiledefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        createSpinner.visibility = View.INVISIBLE

    }

    fun GenerateUserAvatar(view: View){
        val randomColor = Random.nextInt(2);
        val avatarNum = Random.nextInt(28)
        if(randomColor==0){
            userAvatar = "light$avatarNum"
        }
        else{
            userAvatar="dark$avatarNum"
        }
        val resourceId = resources.getIdentifier(userAvatar,"drawable", packageName)
        CreateAvatarImgView.setImageResource(resourceId)
    }

    fun generateBckgrndColor(view: View){
        val r = Random.nextInt(255)
        val g = Random.nextInt(255)
        val b = Random.nextInt(255)

        CreateAvatarImgView.setBackgroundColor(Color.rgb(r,g,b))
        val savedR = r.toDouble() /255
        val savedG = g.toDouble() /255
        val savedB = b.toDouble() /255

        avatarColor = "[$savedR, $savedG, $savedB, 1]"


    }

    fun createUsrBtnClicked(view: View) {
        enableSpinner(true)
        val userName = createUserNameTxt.text.toString()
        val userPassword = createPswrdTxt.text.toString()
        val userEmail = createEmailTxt.text.toString()
        if (userName.isNotEmpty() && userPassword.isNotEmpty() && userEmail.isNotEmpty()){
        AuthService.registerUser(this, userEmail, userPassword) { registerSuccess ->
            if (registerSuccess) {
                AuthService.loginUser(this, userEmail, userPassword) { loginSuccess ->
                    if (loginSuccess) {
                        AuthService.createUser(this, userName, userEmail, userAvatar, avatarColor) { createSuccess ->
                            if (createSuccess) {
                                val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                                LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
                                enableSpinner(false)
                                finish()
                            } else {
                            }
                        }
                    } else {
                        errorToast()
                    }
                }

            } else {
                errorToast()
            }
        }
    } else{
            Toast.makeText(this, "username/email/password found empty, please check", Toast.LENGTH_LONG).show()
            enableSpinner(false)
        }
    }

    fun errorToast(){
        Toast.makeText(this, "something went wrong, please try again!", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean){
        if(enable){
            createSpinner.visibility = View.VISIBLE
            createUsrBtn.isEnabled = false
            CreateAvatarImgView.isEnabled = false
            BackgrndColorBtn.isEnabled = false
        }else{
            createSpinner.visibility = View.INVISIBLE
            createUsrBtn.isEnabled = true
            CreateAvatarImgView.isEnabled = true
            BackgrndColorBtn.isEnabled = true
        }
    }


}
