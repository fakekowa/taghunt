package com.appzet.taghunt.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R
import kotlinx.android.synthetic.main.create_game_activity.*


class CreateGameActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_game_activity)

        val button1 = findViewById<Button>(R.id.create_game_activity_start_game_button)
        button1.setOnClickListener {
            val intent = Intent(this, PreyInGameActivity::class.java)
            startActivity(intent)
        }

        // Create an ArrayAdapter for number of players
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.create_game_activity_array_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        //create_game_activity_spinner.adapter = adapter

        //Attempt to shorten the spinner dropdown list
        //val spinner = findViewById<Spinner>(R.id.create_game_activity_spinner)
        //try {
        //    val popup = Spinner::class.java.getDeclaredField("mPopup")
        //    popup.isAccessible = true
        //    // Get private mPopup member variable and try cast to ListPopupWindow
        //    val popupWindow = popup.get(spinner) as android.widget.ListPopupWindow
        //    // Set popupWindow height to 700px
        //    popupWindow.height = 500
        //}
        //catch(e: Exception){
//
        //}

        CreatePlayableAreaAdapter(adapter)

        CreateGameTimerAdapter(adapter)
    }

    private fun CreatePlayableAreaAdapter(adapter: ArrayAdapter<CharSequence>) {
        // Create an ArrayAdapter for playable area
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.create_game_activity_playablearea_list, android.R.layout.simple_spinner_item
        )
        var editTextFilledExposedDropdown = findViewById<AutoCompleteTextView>(R.id.filled_exposed_dropdown)
        editTextFilledExposedDropdown.setAdapter(adapter2)
    }

    private fun CreateGameTimerAdapter(adapter: ArrayAdapter<CharSequence>) {
        // Create an ArrayAdapter for play time
        val adapter3 = ArrayAdapter.createFromResource(
            this,
            R.array.create_game_activity_array_list3, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner3.adapter = adapter3
    }
}