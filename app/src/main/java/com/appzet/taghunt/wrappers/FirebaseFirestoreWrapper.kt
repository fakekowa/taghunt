package com.appzet.taghunt.wrappers

import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.User
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreWrapper {

    private val db = FirebaseFirestore.getInstance()
    var roomname = "Default"
    var user: User? = null
    var roomSettings: RoomSettings? = null

    fun createUser(_user: User) : Boolean {
        user = _user
        return db.collection(roomname).document(user!!.username)
            .set(user!!).isSuccessful
    }

    fun createRoom(_roomSettings: RoomSettings, _roomname: String): Boolean {
        roomSettings = _roomSettings
        roomname = _roomname

        return db.collection(roomname).document(roomSettings!!.name).set(roomSettings!!).isSuccessful;
    }
}