package com.rachnbalani.chatapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlin.random.Random

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profiledefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
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

    }
}
