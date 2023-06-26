package org.bedu.proyecto01equipo16.login.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    suspend fun newUser(
        name: String,
        phoneNumber: String,
        email: String,
        password: String,
        profilePicture: String
    ): Boolean {
        val user = hashMapOf(
            "name" to name,
            "phoneNumber" to phoneNumber,
            "email" to email,
            //"profilePicture" to profilePicture
        )

        val existUser = db.collection("Users").document(email).get().await()
        if (existUser.data.isNullOrEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
            db.collection("Users").document(email).set(user)
            return true
        }
        return false
    }
}
