package com.rachnbalani.chatapp.Services

import android.graphics.Color
import java.util.*

object UserDataService {

    var id=""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun returnAavatarColor(Components: String): Int{
        val strippedColor = Components
            .replace("[", "")
            .replace("]", "")
            .replace(",", "")

        var r= 0
        var g= 0
        var b=0

        val scanner = Scanner(strippedColor)
        if(scanner.hasNext()){
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r, g, b)
    }

    fun logOut() {
        var id=""
        var avatarColor = ""
        var avatarName = ""
        var email = ""
        var name = ""
        AuthService.authToken = ""
        AuthService.userEmail = ""
        AuthService.isLoggedIn = false
    }

}