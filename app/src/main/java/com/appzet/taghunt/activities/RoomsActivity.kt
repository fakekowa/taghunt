package com.appzet.taghunt.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appzet.taghunt.R
import com.appzet.taghunt.adapters.RoomsAdapter
import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.User
import com.appzet.taghunt.services.FirestoreService
import kotlinx.android.synthetic.main.rooms_activity.*
import org.w3c.dom.Text

class RoomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rooms_activity)
        var user = User("Pontus", true)
        var roomname = "Room 3"
        var roomsettings = RoomSettings(15,user.username, false)
        var db = FirestoreService()

        db.createUser(user)

        roomListRecyclerView.layoutManager = LinearLayoutManager(this)


        db.getRooms { rooms ->
            roomListRecyclerView.adapter = RoomsAdapter(rooms, this)
        }

        var one = roomListRecyclerView.findViewHolderForAdapterPosition(1)

       // one.setOnClickListener{
       //     Toast.makeText(this, one.text, Toast.LENGTH_LONG).show()
       // }
//

    }
}
