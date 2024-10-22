package com.example.enfermeria.ui.screens

/*import Registro
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enfermeria.services.FirebaseService

@Composable
fun ConsultaRegistrosScreen(
    registros: List<Registro>,
    firebaseService: FirebaseService
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lista de Registros", modifier = Modifier.padding(16.dp))

        if (registros.isEmpty()) {
            Text("No hay registros disponibles")
        } else {
            LazyColumn {
                items(registros) { registro ->
                    ListItem(
                        text = {
                            Text(text = registro.descripcion)
                        },
                        secondaryText = {
                            Text(text = "Fecha: ${registro.fecha}")
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
*/