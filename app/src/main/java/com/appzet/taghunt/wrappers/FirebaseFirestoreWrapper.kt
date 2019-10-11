package com.appzet.taghunt.wrappers

import com.appzet.taghunt.dataclass.RoomSettings
import com.appzet.taghunt.dataclass.User
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreWrapper {
    val ROOM_SETTINGS = "RoomSettings"
    private val db = FirebaseFirestore.getInstance()
    var roomname = "Default"
    var user: User? = null
    var roomSettings: RoomSettings? = null
    var username: String? = null

    fun createUser(_user: User) {
        user = _user
        db.collection(roomname).document(user!!.username)
            .set(user!!)
    }

    fun createRoom(roomname: String, user: User) {
        username = user.username
        this.roomname = roomname
        this.user = user


        db.collection(this.roomname).document(username!!)
            .set(this.user!!)
    }

    fun createRoomSettings(roomSettings: RoomSettings, roomname: String){
        this.roomSettings = roomSettings
        this.roomname = roomname


        db.collection(this.roomname).document(ROOM_SETTINGS).set(this.roomSettings!!)


    }
}