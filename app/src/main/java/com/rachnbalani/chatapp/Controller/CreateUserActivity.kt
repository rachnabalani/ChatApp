package com.rachnbalani.chatapp.Controller

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rachnbalani.chatapp.R
import com.rachnbalani.chatapp.Services.AuthService
import com.rachnbalani.chatapp.Services.UserDataService
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

    fun createUsrBtnClicked(view: View){
        val userName = createUserNameTxt.text.toString()
        val userPassword = createPswrdTxt.text.toString()
        val userEmail = createEmailTxt.text.toString()
        AuthService.registerUser(this, userEmail, userPassword){ registerSuccess ->
            if(registerSuccess){
                AuthService.loginUser(this, userEmail, userPassword) { loginSuccess ->
                    if(loginSuccess){
                        AuthService.createUser(this, userName, userEmail, userAvatar, avatarColor) { createSuccess ->
                            if(createSuccess){
                                println("User registered, logged in and created successfully. ")
                                println(UserDataService.avatarName)
                                println(UserDataService.avatarColor)
                                println(UserDataService.name)
                                finish()
                            }
                            else{
                                println("something failed!!!")
                            }
                        }
                    }
                }

            }
        }
    }

    fun enableSpinner(enable: Boolean){
        if(enable){
            createSpinner.visibility = View.VISIBLE
            createUsrBtn.isEnabled = false
            CreateAvatarImgView.isEnabled = false
            BackgrndColorBtn.isEnabled = false
        }
    }


}
