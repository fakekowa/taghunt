package com.appzet.taghunt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.appzet.taghunt.wrappers.FirebaseFirestoreWrapper
import com.appzet.taghunt.R

class MainActivity : AppCompatActivity() {
    val db = FirebaseFirestoreWrapper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val joinGameButton = findViewById<Button>(R.id.join_game_button)
        val createGameButton = findViewById<Button>(R.id.create_game_button)

        joinGameButton.setOnClickListener{

           val intent =  Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}
