package com.example.enfermeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.enfermeria.data.model.Enfermera
import com.example.enfermeria.repository.EnfermeraRepository
import com.example.enfermeria.services.FirebaseService
import com.example.enfermeria.ui.screens.ConsultaRegistrosScreen
import com.example.enfermeria.ui.theme.EnfermeriaTheme
import com.example.enfermeria.ui.theme.enfermera.EliminarRegistroScreen
import com.example.recordatorio.services.UserService
import com.example.recordatorio.ui.theme.screens.LoginScreen

class MainActivity : ComponentActivity() {

    private val firebaseService = FirebaseService()
    private val userService = UserService(firebaseService)
    private val enfermeraRepository = EnfermeraRepository(firebaseService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseService.initialize() // Inicializar Firebase

        setContent {
            // Especificar el tipo explícitamente para evitar errores de tipo
            var currentScreen by remember { mutableStateOf<Screen>(Screen.Login) }
            var enfermeras by remember { mutableStateOf<List<Enfermera>>(emptyList()) }
            var enfermeraToDelete by remember { mutableStateOf<Enfermera?>(null) }

            EnfermeriaTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        is Screen.Login -> {
                            LoginScreen(
                                onLoginClick = {
                                    // Acción al iniciar sesión
                                    currentScreen = Screen.EnfermeraList
                                    loadEnfermeras() // Cargar enfermeras después del inicio de sesión
                                },
                                onRecoverClick = {
                                    // Acción para recuperar contraseña
                                    // Ejemplo: Redirigir a pantalla de recuperación
                                },
                                userService = userService
                            )
                        }
                        is Screen.EnfermeraList -> {
                            ConsultaRegistrosScreen(
                                registros = enfermeras,
                                firebaseService = firebaseService
                            )
                        }
                        is Screen.EliminarEnfermera -> {
                            EliminarRegistroScreen(
                                enfermera = (currentScreen as Screen.EliminarEnfermera).enfermera,
                                onBackClick = { currentScreen = Screen.EnfermeraList } // Navegación de vuelta
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadEnfermeras() {
        enfermeraRepository.getEnfermeras { enfermerasList ->
            enfermeras = enfermerasList
        }
    }
}

// Enum para las pantallas
sealed class Screen {
    object Login : Screen()
    object EnfermeraList : Screen()
    data class EliminarEnfermera(val enfermera: Enfermera) : Screen()
}
