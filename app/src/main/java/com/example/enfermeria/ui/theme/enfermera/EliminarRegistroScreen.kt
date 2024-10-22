package com.example.enfermeria.ui.theme.enfermera

import Registro
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enfermeria.services.FirebaseService

@Composable
fun EliminarRegistroScreen(
    registro: Registro,
    onDeleteConfirm: () -> Unit,
    onCancel: () -> Unit,
    firebaseService: FirebaseService // Debes tener FirebaseService implementado
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¿Estás seguro que deseas eliminar este registro?", modifier = Modifier.padding(16.dp))

        Button(
            onClick = { onDeleteConfirm() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Eliminar")
        }

        Button(
            onClick = { onCancel() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Cancelar")
        }
    }
}
