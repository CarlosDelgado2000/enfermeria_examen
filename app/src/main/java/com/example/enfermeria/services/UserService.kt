package com.example.recordatorio.services

import com.example.enfermeria.services.FirebaseService
import com.google.firebase.auth.FirebaseAuth

class UserService(firebaseService: FirebaseService) {

    // Método para verificar al usuario (inicio de sesión) utilizando Firebase
    fun verifyUser(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null) // Éxito en la autenticación
                } else {
                    val errorMessage = task.exception?.message ?: "Error al iniciar sesión"
                    onComplete(false, errorMessage) // Error en la autenticación
                }
            }
    }
}
