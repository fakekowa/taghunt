package com.appzet.taghunt

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreWrapper {

    private val db = FirebaseFirestore.getInstance()
    var roomname = "Default"

    fun createUser(username: String, hashmap: HashMap<String, Any>) {
        db.collection(roomname).document(username)
            .set(hashmap)
    }
}