package com.appzet.taghunt.services

import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.Rooms
import com.appzet.taghunt.dataclass.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreService {
    private val db = FirebaseFirestore.getInstance()
    var rootcollection = "Rooms"
    var roomname = "Default"
    var user: User? = null
    var roomsettings: RoomSettings? = null
    var username: String? = null
    var room: Rooms? = null

    fun createUser(_user: User) {
        user = User("kalle", false)
        db.collection(rootcollection).document("Room 3").collection("User").document(user!!.username).set(user!!)
    }

    fun createRoom(_roomname: String, _user: User, _roomSettings: RoomSettings) {
        user = _user
        roomname =_roomname
        roomsettings = _roomSettings

        db.collection(rootcollection).document(roomname).collection("User").document(user!!.username).set(user!!)
        db.collection(rootcollection).document(roomname).set(roomsettings!!)
    }

    fun getRooms(myCallback: (MutableList<String>) -> Unit){
        db.collection(rootcollection).get().addOnCompleteListener{ rooms ->
            if(rooms.isSuccessful) {
                val roomNames = mutableListOf<String>()
                for (room in rooms.result!!) roomNames.add(room.id)
                myCallback(roomNames)
            }
        }
    }
}