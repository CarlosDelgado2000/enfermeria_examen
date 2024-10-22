package com.example.enfermeria.services

import Registro
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await

class FirebaseService {

    private val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance().apply {
            setPersistenceEnabled(true) // Habilita la persistencia local
        }
    }

    private val enfermeraReference: DatabaseReference by lazy {
        database.getReference("enfermeras") // Referencia a la colección "enfermeras" en Firebase
    }

    fun initialize() {
        // Aquí puedes implementar la inicialización de Firebase si es necesario
    }

    fun deleteEnfermera(enfermeraId: String) {
        // Implementación para eliminar una enfermera en Firebase
        enfermeraReference.child(enfermeraId).removeValue()
    }

    suspend fun getAllRegistros(): List<Registro> {
        // Implementación para obtener todos los registros desde Firebase
        val registros = mutableListOf<Registro>()
        enfermeraReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val registro = data.getValue(Registro::class.java)
                    registro?.let { registros.add(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores, si es necesario
            }
        })
        return registros
    }

    suspend fun addRegistro(registro: Registro) {
        // Implementación para agregar un registro en Firebase
        val key = enfermeraReference.push().key
        key?.let {
            enfermeraReference.child(it).setValue(registro).await()
        }
    }
}
