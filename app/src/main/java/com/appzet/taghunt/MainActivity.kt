package com.appzet.taghunt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "MainActivity"
    }

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)


        button.setOnClickListener{

            var _first = editText.text.toString()
            addToFirebase(_first, "mylastname")
        }
    }

    fun addToFirebase(first: String, second: String) {
        var user = hashMapOf(
            "first" to first,
            "second" to second
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
