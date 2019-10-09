package com.appzet.taghunt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.appzet.taghunt.R
import com.appzet.taghunt.wrappers.FirebaseFirestoreWrapper
import com.google.android.material.textfield.TextInputEditText

class JoinActivity : AppCompatActivity() {
    val db = FirebaseFirestoreWrapper()
    val button = this!!.findViewById<Button>(R.id.create_user_button)
    val usernameList: ArrayList<String> = arrayListOf()
    var username = this!!.findViewById<TextInputEditText>(R.id.enter_username_editText)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join_game_activity)


        button.setOnClickListener(){
            usernameList.add(username.text.toString())
            usernameList.add("Aleksander")
            usernameList.add("Pontus")
            usernameList.add("Baloo")
            usernameList.add("Bagheera")
            usernameList.add("Sherikhan")
            usernameList.add("Mowgli")
            usernameList.add("King Louie")

            val intent1 = Intent(this, LobbyActivity::class.java)
            intent1.putStringArrayListExtra("username", usernameList)
            startActivity(intent1)
        }
    }
}
