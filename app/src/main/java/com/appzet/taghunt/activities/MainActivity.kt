package com.appzet.taghunt.activities

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.appzet.taghunt.R
import com.appzet.taghunt.Services.LocationService
import com.appzet.taghunt.Services.RuntimePermissionService

class MainActivity : AppCompatActivity() {
    val permission = RuntimePermissionService(this)
    val loc = LocationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val joinGameButton = findViewById<Button>(R.id.join_game_button)
        val createGameButton = findViewById<Button>(R.id.create_game_button)

        joinGameButton.setOnClickListener{
            permission.checkPermission()
           val intent =  Intent(this, RoomsActivity::class.java)
           startActivity(intent)
        }
        createGameButton.setOnClickListener {
            val intent2 = Intent(this, CreateGameActivity::class.java)
            startActivity(intent2)
        }
    }

        override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            permission.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    for(i in 0 until 2) {
                        permission.checkPermission()
                    }
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }


    }
}
