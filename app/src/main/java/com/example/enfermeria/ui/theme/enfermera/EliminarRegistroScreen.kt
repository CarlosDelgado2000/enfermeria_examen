package com.example.enfermeria.ui.theme.enfermera

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enfermeria.data.model.Enfermera
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun EliminarRegistroScreen(
    enfermera: Enfermera,
    onBackClick: () -> Unit
) {
    var Enfermera by remember { mutableStateOf(listOf<Enfermera>()) }

    // Obtener datos desde Firestore
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("medicamentos")
            .get()
            .addOnSuccessListener { result ->
                Enfermera = result.map { document ->
                    Enfermera(
                        id = document.id,
                        nombre = document.getString("nombre") ?: "",
                        dosis = document.getString("dosis") ?: ""
                    )
                }
            }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Eliminar Registro")

        // Lista de medicamentos
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(Enfermera) { medicamento ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Nombre: ${medicamento.nombre}")
                    Button(onClick = {
                        // Eliminar medicamento de Firestore
                        Firebase.firestore.collection("medicamentos")
                            .document(medicamento.id)
                            .delete()
                    }) {
                        Text(text = "Eliminar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de regreso
        Button(onClick = onBackClick) {
            Text(text = "Volver")
        }
    }
}
