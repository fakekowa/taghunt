package com.appzet.taghunt.activities

import android.location.Location
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.Services.LocationService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.join_game_dialog.*
import kotlinx.android.synthetic.main.prey_in_game_activity.*


class PreyInGameActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationService: LocationService? = null
    var marker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appzet.taghunt.R.layout.prey_in_game_activity)

        loadMap()
        loadTimer(15)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        locationService = LocationService(mMap)
        locationService!!.startLocationUpdates(this, 5000)
        locationService!!.onLocationChanged()

        // Add a marker in Sydney and move the camera
        val gbg = LatLng(11.0, 22.0)
        marker = mMap.addMarker(MarkerOptions().position(gbg).title("Marker in Gothenburg"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbg))
        // Set a preference for minimum and maximum zoom.
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.maxZoomLevel
    }

    fun loadMap() {
        //Load in the map
        val mapFragment = (supportFragmentManager
            .findFragmentById(com.appzet.taghunt.R.id.preyInGameMap) as SupportMapFragment?)
        mapFragment?.getMapAsync(this)
    }

    fun loadTimer(minutes: Long) {
        //Start chronometer countdown
        view_timer.isCountDown = true
        view_timer.base = SystemClock.elapsedRealtime() + minutes*1000*60
        view_timer.onFinishTemporaryDetach()
        view_timer.start()
        view_timer.setOnChronometerTickListener {
            if (view_timer.base < SystemClock.elapsedRealtime()){
                view_timer.stop()
                locationService = null
                try{
                    locationService!!.stoplocationUpdates()
                }
                catch(e: Exception){
                    Toast.makeText(this, "locationService was null, uh-oh something is wrong, please restart the app", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

