package com.appzet.taghunt.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R
import com.google.android.material.textfield.TextInputEditText


class CreateGameActivity: AppCompatActivity() {

    var editTextFilledExposedDropdown: AutoCompleteTextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_game_activity)

        val textInputEditText = findViewById<TextInputEditText>(R.id.createGameActivityTextInput)
        val button1 = findViewById<Button>(R.id.createGameActivityStartGameButton)
        button1.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }

        //Focus username input and show keyboard
        textInputEditText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)


        //Populate the "Spinners"
        CreatePlayerAdapter()
        CreatePlayableAreaAdapter()
        CreatePlayTimeAdapter()
    }

    private fun CreatePlayerAdapter() {
        // Create an ArrayAdapter for playable area
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.create_game_activity_No_Players_list, android.R.layout.simple_spinner_item
        )
        editTextFilledExposedDropdown =
            findViewById<AutoCompleteTextView>(R.id.createGamePlayerDropdown)
        editTextFilledExposedDropdown!!.setAdapter(adapter)
    }
    private fun CreatePlayableAreaAdapter() {
        // Create an ArrayAdapter for playable area
        val adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.create_game_activity_playablearea_list, android.R.layout.simple_spinner_item
        )
        var editTextFilledExposedDropdown =
            findViewById<AutoCompleteTextView>(R.id.createGameActivityPlayAreaDropdown)
        editTextFilledExposedDropdown.setAdapter(adapter1)
    }
    private fun CreatePlayTimeAdapter() {
        // Create an ArrayAdapter for playable area
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.create_game_activity_play_time_list, android.R.layout.simple_spinner_item
        )
        var editTextFilledExposedDropdown =
            findViewById<AutoCompleteTextView>(R.id.createGameActivityPlayTimeDropdown)
        editTextFilledExposedDropdown.setAdapter(adapter2)
    }

}