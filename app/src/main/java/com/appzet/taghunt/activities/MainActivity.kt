package com.appzet.taghunt.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R
import com.appzet.taghunt.Services.RuntimePermissionService
import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.User
import com.appzet.taghunt.services.FirestoreService

class MainActivity : AppCompatActivity(){

    var permission: RuntimePermissionService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val user = User("Pontus", true)
        val roomname = "Room 3"
        val roomsettings = RoomSettings(15,user.username, false)
        val db = FirestoreService()
        db.createRoom(roomname, user, roomsettings)

        permission = RuntimePermissionService(this)

        permission!!.checkPermission()

        val joinGameButton = findViewById<Button>(R.id.join_game_button)
        val createGameButton = findViewById<Button>(R.id.create_game_button)

        joinGameButton.setOnClickListener {
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }
        createGameButton.setOnClickListener {
            val intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }
    }
}