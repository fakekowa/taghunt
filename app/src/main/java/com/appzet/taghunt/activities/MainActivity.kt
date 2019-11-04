package com.appzet.taghunt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        val db = FirestoreService()

        val user = User("Pontus", 0, 0, true)
        val user2 = user.copy("kalle", creator = false)
        val room = RoomSettings(15, "Pontus", false)

        //db.createRoom("Test Room", user)
        //db.createRoomSettings(room, "Test Room")
        db.createUser(user2)

        permission = RuntimePermissionService(this)

        permission!!.checkPermission()

        val joinGameButton = findViewById<Button>(R.id.join_game_button)
        val createGameButton = findViewById<Button>(R.id.create_game_button)

        joinGameButton.setOnClickListener {
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }
        createGameButton.setOnClickListener {
            val intent2 = Intent(this, CreateGameActivity::class.java)
            startActivity(intent2)
        }
    }
}