package com.example.enfermeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import com.example.enfermeria.data.model.Enfermera
import com.example.enfermeria.repository.EnfermeraRepository
import com.example.enfermeria.services.FirebaseService
import com.example.enfermeria.ui.theme.EnfermeriaTheme
import com.example.recordatorio.screens.LoginScreen
import com.example.recordatorio.services.UserService

class MainActivity : ComponentActivity() {

    private val firebaseService = FirebaseService()
    private val userService = UserService(firebaseService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseService.initialize() // Inicializar Firebase

        setContent {
            var currentScreen by remember { mutableStateOf(Screen.Login) }
            var enfermeras by remember { mutableStateOf(emptyList<Enfermera>()) }
            var enfermeraToDelete by remember { mutableStateOf<Enfermera?>(null) }

            EnfermeriaTheme {
                Column(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        Screen.Login -> {
                            LoginScreen(
                                onLoginSuccess = { },
                                userService = userService
                            )
                        }
                    }
                }
            }
        }
    }
}

// Enum para las pantallas
sealed class Screen {
    object Login : Screen()
    object EnfermeraList : Screen()
    data class EliminarEnfermera(val enfermera: Enfermera) : Screen()
}
