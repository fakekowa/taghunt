package com.appzet.taghunt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.appzet.taghunt.R
import kotlinx.android.synthetic.main.listview_item.*

class LobbyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby_activity)
        var listView = findViewById<ListView>(R.id.lobbyActivityList)
        val username = intent.getStringExtra("username")

        listView.addView(listviewItemTextView)

        listviewItemTextView.text = username


    }
}
