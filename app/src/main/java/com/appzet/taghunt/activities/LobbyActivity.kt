package com.appzet.taghunt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.appzet.taghunt.R
import kotlinx.android.synthetic.main.listview_item.*

class LobbyActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby_activity)
        listView = findViewById<ListView>(R.id.lobbyActivityList)
        var playerCount = findViewById<TextView>(R.id.lobby_player_number_text)
        val username = intent.getStringArrayListExtra("username")

        playerCount.text = username.size.toString() + "/25"
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, username)

        listView.adapter = adapter




    }
}
