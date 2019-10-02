package com.appzet.taghunt.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.appzet.taghunt.R
import com.appzet.taghunt.Services.RuntimePermissionService

class MainActivity : AppCompatActivity(){

    var permission: RuntimePermissionService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

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