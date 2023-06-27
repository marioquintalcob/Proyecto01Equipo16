package org.bedu.proyecto01equipo16.login.data

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import org.bedu.proyecto01equipo16.login.model.User


class UserRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    suspend fun registerNewUser(
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

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await() != null
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            false
        }
    }

    suspend fun getUser(email: String): User? {
        val reference = db.collection("Users").document(email).get().await()
        if (!reference.data.isNullOrEmpty()) {
            val name = reference.get("name") as String
            val phoneNumber = reference.get("phoneNumber") as String
            val auxEmail = reference.get("email") as String
            saveToken(email)
            return User(name, phoneNumber, auxEmail, "")
        }
        return null
    }

    private suspend fun saveToken(email: String) {
        val token = FirebaseMessaging.getInstance().token.await()
        db.collection("Users").document(email).update("token", token)
    }
}
