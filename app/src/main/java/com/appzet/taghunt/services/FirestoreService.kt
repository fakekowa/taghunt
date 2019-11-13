package com.appzet.taghunt.services

import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {
    val ROOM_SETTINGS = "RoomSettings"
    private val db = FirebaseFirestore.getInstance()
    var rootcollection = "Rooms"
    var roomname = "Default"
    var user: User? = null
    var roomSettings: RoomSettings? = null
    var username: String? = null

    fun createUser(_user: User) {
        user = _user
        db.collection(rootcollection).document(roomname).update("users", FieldValue.arrayUnion(user!!))
    }

    fun createRoom(roomname: String, user: User) {
        username = user.username
        this.roomname = roomname
        this.user = user


        db.collection(rootcollection).document(username!!)
            .set(this.user!!)
    }

    fun createRoomSettings(roomSettings: RoomSettings, roomname: String){
        this.roomSettings = roomSettings
        this.roomname = roomname


        db.collection(this.roomname).document(ROOM_SETTINGS).set(this.roomSettings!!)
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