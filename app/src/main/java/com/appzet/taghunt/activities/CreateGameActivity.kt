package com.appzet.taghunt.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.appzet.taghunt.R
import com.appzet.taghunt.Services.LocationService
import com.appzet.taghunt.Services.RuntimePermissionService
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.create_game_activity.*
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.Spinner
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.tasks.Task




class CreateGameActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var permission: RuntimePermissionService? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    lateinit var mLastLocation: Location
    internal lateinit var mLocationRequest: LocationRequest
    val started: Boolean = false
    var gbg: LatLng = LatLng(0.0, 0.0)
    var markerOption: MarkerOptions = MarkerOptions()
    var marker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appzet.taghunt.R.layout.create_game_activity)

        val button = findViewById<Button>(R.id.create_game_button)

        permission = RuntimePermissionService(this)
        permission!!.checkPermissionForLocation()
        val mapFragment = supportFragmentManager
            .findFragmentById(com.appzet.taghunt.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(this,
            com.appzet.taghunt.R.array.create_game_activity_array_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner.adapter = adapter
        fun getValues(view: View) {
            Toast.makeText(this, "Spinner 1 " + create_game_activity_spinner.selectedItem.toString(),
                Toast.LENGTH_LONG).show()
        }
        val spinner = findViewById<Spinner>(com.appzet.taghunt.R.id.create_game_activity_spinner)
        try {
            val popup = Spinner::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true

            // Get private mPopup member variable and try cast to ListPopupWindow
            val popupWindow = popup.get(spinner) as android.widget.ListPopupWindow

            // Set popupWindow height to 500px
            popupWindow.height = 700
        } catch (e: NoClassDefFoundError) {
            // silently fail...
        } catch (e: ClassCastException) {
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }


        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }

        button.setOnClickListener{
            if(permission!!.checkPermissionForLocation() && !started) {
                startLocationUpdates()
            }
            else{
                stoplocationUpdates()
            }
        }
    }

//    private LatLngBounds AUSTRALIA = new LatLngBounds(
//    new LatLng(-44, 113), new LatLng(-10, 154));

// Set the camera to the greatest possible zoom level that includes the
// bounds
//    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 0));

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val gbg = LatLng(11.0, 22.0)
        marker = mMap.addMarker(MarkerOptions().position(gbg).title("Marker in Gothenburg"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbg))

        // Set a preference for minimum and maximum zoom.
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.maxZoomLevel
    }

    //  fun getValues(view: View) {
    //     Toast.makeText(this, "Spinner 1 " + create_game_activity_spinner.selectedItem.toString(),
    //         Toast.LENGTH_LONG).show()
    //  }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permission!!.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have to add startlocationUpdate() method later instead of Toast
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildAlertMessageNoGps() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 11)
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                finish()
            }
        val alert: AlertDialog  = builder.create()
        alert.show()


    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // do work here
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    private fun stoplocationUpdates() {
        mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
    }

    protected fun startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = LocationRequest()
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest!!.setInterval(10000)
        mLocationRequest!!.setFastestInterval(8000)

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return
        }
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback,
            Looper.myLooper())
    }


    fun onLocationChanged(location: Location) {
        // New location has now been determined

        mLastLocation = location
        gbg = LatLng(mLastLocation.latitude, mLastLocation.longitude)
        marker!!.position = gbg
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbg))
        if (mLastLocation != null) {
// Update the UI from here
        }


    }
}