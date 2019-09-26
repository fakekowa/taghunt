package com.appzet.taghunt.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.create_game_activity.*
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.Spinner
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class CreateGameActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appzet.taghunt.R.layout.create_game_activity)

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        val gbg = LatLng(57.708870, 11.974560)
        mMap.addMarker(MarkerOptions().position(gbg).title("Marker in Gothenburg"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbg))

        // Set a preference for minimum and maximum zoom.
        mMap.setMinZoomPreference(14.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.maxZoomLevel
    }

}