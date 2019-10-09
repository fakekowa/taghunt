package com.appzet.taghunt.activities

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appzet.taghunt.R

class RoomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rooms_activity)

        showDialog()
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.join_game_dialog)
        dialog.show()

        val joinBtn = dialog.findViewById(R.id.joinGameDialogButton) as Button
        joinBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}
