package com.appzet.taghunt.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.appzet.taghunt.R

class RoomsActivity : AppCompatActivity() {

    val dialog = Dialog(this)
    val joinBtn = dialog.findViewById(R.id.joinGameDialogButton) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rooms_activity)

        showDialog("thisismytitle")
    }

    private fun showDialog(title: String) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.join_game_dialog)
        dialog.show()

        joinBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}
