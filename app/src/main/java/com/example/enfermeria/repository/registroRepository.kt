package com.example.enfermeria.repository

import Registro
import com.example.enfermeria.services.FirebaseService

class RegistroRepository(private val firebaseService: FirebaseService) {

    suspend fun getAllRegistros(): List<Registro> {
        return firebaseService.getAllRegistros()
    }

    suspend fun addRegistro(registro: Registro) {
        firebaseService.addRegistro(registro)
    }
}
