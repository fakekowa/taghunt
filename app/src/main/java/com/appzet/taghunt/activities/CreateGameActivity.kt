package com.appzet.taghunt.activities

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R
import com.appzet.taghunt.Services.RuntimePermissionService
import com.appzet.taghunt.wrappers.FirebaseFirestoreWrapper
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.create_game_activity.*


class CreateGameActivity: AppCompatActivity() {

    var permission: RuntimePermissionService? = null
    var markerOption: MarkerOptions = MarkerOptions()
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


        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
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
}