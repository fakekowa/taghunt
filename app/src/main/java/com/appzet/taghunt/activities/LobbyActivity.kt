package com.appzet.taghunt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.appzet.taghunt.R
import kotlinx.android.synthetic.main.listview_item.*

class LobbyActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.lobby_activity)

       val button1 = findViewById<Button>(R.id.lobbyActivityStartGameButton)
       button1.setOnClickListener {
           val intent = Intent(this, PreyInGameActivity::class.java)
           startActivity(intent)
       }
    }
}
