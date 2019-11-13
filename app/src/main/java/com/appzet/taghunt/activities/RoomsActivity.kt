package com.appzet.taghunt.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        roomListRecyclerView.layoutManager = LinearLayoutManager(this)


        db.getRooms { rooms ->
            roomListRecyclerView.adapter = RoomsAdapter(rooms, this)
        }

 //      roomListRecyclerView.adapter.onBindViewHolder(  ) {
 //          val intent = Intent(this, LobbyActivity::class.java)
 //          startActivity(intent)
 //      }

        var haha = "hej"

        //showDialog()
    }
   //private fun showDialog() {
   //    val dialog = Dialog(R.layout.join_game_dialog)
   //    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
   //    dialog.setCancelable(false)
   //    dialog.setContentView(R.layout.join_game_dialog)
   //    dialog.show(
   //    val joinBtn = findViewById(R.id.joinGameDialogButton) as Button
   //    joinBtn.setOnClickListener {
   //        dialog.dismiss()
   //    }
   // }
}
