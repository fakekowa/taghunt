package com.appzet.taghunt.Services

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class LocationService(mMap: GoogleMap) {

    internal var lastLocation: Location = Location("default")
    internal var map = mMap
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    internal lateinit var mLocationRequest: LocationRequest
    var gbg: LatLng = LatLng(0.0, 0.0)

    fun startLocationUpdates(activity: Activity, frequency: Long) {

        // Create the location request to start receiving updates
        mLocationRequest = LocationRequest()
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest!!.setInterval(frequency)
        mLocationRequest!!.setFastestInterval(frequency)

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(activity)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return
        }
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback,
            Looper.myLooper())
    }

    fun stoplocationUpdates() {
        mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // do work here
            lastLocation = locationResult.lastLocation
            //TODO need to add mMap here
            onLocationChanged()
        }
    }

    fun onLocationChanged() {
        // New location has now been determined
        var haha: Location? = lastLocation
        gbg = LatLng(lastLocation.latitude, lastLocation.longitude)
        map.moveCamera(CameraUpdateFactory.newLatLng(gbg))
    }
}