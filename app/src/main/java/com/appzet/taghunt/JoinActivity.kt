package com.appzet.taghunt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join_game_activity)

        val button = findViewById<Button>(R.id.create_user_button)

        button.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}
