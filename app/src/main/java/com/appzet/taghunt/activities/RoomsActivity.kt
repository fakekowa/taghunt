package com.appzet.taghunt.activities

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appzet.taghunt.R
import com.appzet.taghunt.adapters.RoomsAdapter
import com.appzet.taghunt.services.FirestoreService
import kotlinx.android.synthetic.main.rooms_activity.*

class RoomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rooms_activity)
        var db = FirestoreService()

        val rooms: ArrayList<String> = ArrayList()

        roomListRecyclerView.layoutManager = LinearLayoutManager(this)
        roomListRecyclerView.adapter = RoomsAdapter(rooms, this)

        var roomList = db.getRooms()

        var haha = "hej"

        //showDialog()
    }

   // private fun showDialog() {
    //   val dialog = Dialog(this)
    //   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    //   dialog.setCancelable(false)
    //   dialog.setContentView(R.layout.join_game_dialog)
    //   dialog.show()

    //   val joinBtn = dialog.findViewById(R.id.joinGameDialogButton) as Button
    //   joinBtn.setOnClickListener {
    //       dialog.dismiss()
    //   }
    //}
}
