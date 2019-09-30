package com.appzet.taghunt.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
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
import kotlinx.android.synthetic.main.create_game_activity.*
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.Spinner
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.ArrayAdapter
import com.appzet.taghunt.wrappers.FirebaseFirestoreWrapper
import com.google.android.gms.maps.model.*
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
    val firestore = FirebaseFirestoreWrapper()
    var hashmap: HashMap<String, Any> = HashMap()
    var username = "John Doe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_game_activity)

        val button1 = findViewById<Button>(R.id.create_game_activity_start_game_button)
        button1.setOnClickListener {
            val intent = Intent(this, PreyInGameActivity::class.java)
            startActivity(intent)
        }

        //Check permission for traching location
        permission = RuntimePermissionService(this)
        permission!!.checkPermissionForLocation()
        //Load in the map?
        val mapFragment = supportFragmentManager
            .findFragmentById(com.appzet.taghunt.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Create an ArrayAdapter for number of players
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.create_game_activity_array_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner.adapter = adapter

        //Attempt to shorten the spinner dropdown list
        val spinner = findViewById<Spinner>(R.id.create_game_activity_spinner)
        try {
            val popup = Spinner::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            // Get private mPopup member variable and try cast to ListPopupWindow
            val popupWindow = popup.get(spinner) as android.widget.ListPopupWindow
            // Set popupWindow height to 700px
            popupWindow.height = 500
        }
        catch (e: NoClassDefFoundError) {
            // silently fail...
        } catch (e: ClassCastException) {
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }

        // Create an ArrayAdapter for playable area
        val adapter2 = ArrayAdapter.createFromResource(this,
            R.array.create_game_activity_array_list2, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner2.adapter = adapter2

        // Create an ArrayAdapter for play time
        val adapter3 = ArrayAdapter.createFromResource(this,
            R.array.create_game_activity_array_list3, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner3.adapter = adapter3


            //Kod som ska mäta avståndet mellan två long,lat positioner. Tänkte att man kan vända på den för att beräkna long,lat från avståndet?
    //    public static String getDistance(LatLng latlngA, LatLng latlngB) {
   //       Location locationA = new Location("point A");

   //       locationA.setLatitude(latlngA.latitude);
   //       locationA.setLongitude(latlngA.longitude);

   //       Location locationB = new Location("point B");

   //       locationB.setLatitude(latlngB.latitude);
   //       locationB.setLongitude(latlngB.longitude);

   //       float distance = locationA.distanceTo(locationB)/1000; //To convert Meter in Kilometer
   //       return String.format("%.2f", distance);
   //   }


        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }

   //     button1.setOnClickListener{
   //         if(permission!!.checkPermissionForLocation() && !started) {
   //             startLocationUpdates()
   //         }
   //         else{
   //             stoplocationUpdates()
   //         }
   //     }
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
         hashmap = hashMapOf(
             "Longtitude" to location.longitude,
             "Latitude" to location.latitude
        )
        gbg = LatLng(mLastLocation.latitude, mLastLocation.longitude)
        marker!!.position = gbg
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbg))
        firestore.createUser(username, hashmap)
        if (mLastLocation != null) {
// Update the UI from here
        }


    }
}