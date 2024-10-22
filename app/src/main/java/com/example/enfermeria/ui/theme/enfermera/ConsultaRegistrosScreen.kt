package com.example.enfermeria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enfermeria.data.model.Enfermera
import com.example.enfermeria.services.FirebaseService

@Composable
fun ConsultaRegistrosScreen(
    registros: List<Enfermera>,
    firebaseService: FirebaseService
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Registros",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (registros.isEmpty()) {
            Text(text = "No hay registros disponibles", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(registros) { registro ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Descripción: ${registro.descripcion}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Fecha: ${registro.fecha}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}
