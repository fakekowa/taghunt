package com.appzet.taghunt.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R
import kotlinx.android.synthetic.main.create_game_activity.*

class CreateGameActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_game_activity)

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.create_game_activity_array_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        create_game_activity_spinner.adapter = adapter

    }

    fun getValues(view: View) {
       Toast.makeText(this, "Spinner 1 " + create_game_activity_spinner.selectedItem.toString(),
           Toast.LENGTH_LONG).show()
    }
}