package com.example.enfermeria.repository

import com.example.enfermeria.services.FirebaseService

class EnfermeraRepository(private val firebaseService: FirebaseService) {

    suspend fun deleteEnfermera(enfermeraId: String) {
        firebaseService.deleteEnfermera(enfermeraId)
    }
}
