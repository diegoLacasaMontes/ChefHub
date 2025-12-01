package com.diego.chefhub.VIEJO_screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diego.chefhub.R
import com.diego.chefhub.VIEJO_screens.components.ClickableText
import com.diego.chefhub.VIEJO_screens.components.PasswordTextField
import com.diego.chefhub.VIEJO_screens.components.SimpleButton
import com.diego.chefhub.VIEJO_screens.components.SimpleTextField
import com.diego.chefhub.VIEJO_screens.components.VerticalSpacer
import com.diego.chefhub.VIEJO_screens.components.loadCredentials
import com.diego.chefhub.VIEJO_screens.components.saveCredentials
import com.diego.chefhub.VIEJO_screens.components.showMessage
import com.diego.chefhub.ui.AppViewModel
import kotlin.system.exitProcess

@Composable
fun LoginScreen(navController: NavController, appViewModel: AppViewModel) {
    val context = LocalContext.current

    // Solicitud de permisos al inicio.
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach { entry ->
            if (!entry.value) {
                showMessage(context, "Permiso ${entry.key} denegado. Algunas funcionalidades puedes no estar disponibles.")
            }
        }
    }

    // Estado para controlar la solicitud de permisos.
    var permissionsRequested by remember { mutableStateOf(false) }

    //Lista de permisos según la versión del sistema.
    var permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    // Se lanza una solicitud de permisos al montar la composición.
    LaunchedEffect(Unit) {
        if (!permissionsRequested) {
            permissionLauncher.launch(permissions)
            permissionsRequested = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginContent(navController, appViewModel)
    }
}

@Composable
private fun LoginContent(navController: NavController, appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()
    val context = LocalContext.current
    var loadedCredentials by remember { mutableStateOf(false) }

    // COMENTARIO.
    LaunchedEffect(Unit) {
        if (!loadedCredentials) {
            val (savedEmail, savedPassword) = loadCredentials(context)
            if (savedEmail != null && savedPassword != null) {
                appViewModel.onUserChanged(savedEmail, "email")
                appViewModel.onUserChanged(savedPassword, "password")
            }
            loadedCredentials = true
        }
    }

    // COMENTARIO.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // COMENTARIO.
        Image(
            painter = painterResource(id = R.drawable.logo_no_bg),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp)
        )
        VerticalSpacer(40)

        // COMENTARIO.
        SimpleTextField(
            value = appUiState.email,
            onValueChange = { appViewModel.onUserChanged(it, "email") },
            label = "Correo electrónico",
            required = true
        )
        VerticalSpacer(20)

        // COMENTARIO.
        PasswordTextField(
            value = appUiState.paswword,
            onValueChange = { appViewModel.onUserChanged(it, "password") },
            label = "Contraseña",
            required = true
        )
        VerticalSpacer(20)

        // COMENTARIO.
        SimpleButton(
            text = "Iniciar sesión",
            onClick = {
                // COMENTARIO.
                appViewModel.checkLogin { validation ->
                    when(validation) {
                        1 -> showMessage(context, "Uno o más campos están vacíos.")
                        2 -> showMessage(context, "Correo no encontrado en la base de datos.")
                        3 -> if (appUiState.tries != 0) showMessage(context, "Contraseña incorrecta.\nIntentos restantes: ${appUiState.tries - 1}.")
                        4 -> showMessage(context, "Erros inesperado. Por favor, contacte con soporte técnico.")
                        else -> {
                            saveCredentials(context, appUiState.email, appUiState.paswword)
                            showMessage(context, "Inicio de sesión exitoso.")
                        }
                    }

                    // COMENTARIO.
                    if (appUiState.tries <= 1) {
                        showMessage(context, "Se agotaron los intentos. La aplicación se cerrará.")
                        exitProcess(0)
                    }
                }
            }
        )
        VerticalSpacer(20)

        // COMENTARIO.
        ClickableText(
            message = "¿No tienes una cuenta? ",
            link = "Registrarse",
            onClick = {
                appViewModel.resetUserValues()
            }
        )

        // COMENTARIO.
        ClickableText(
            message = "¿Has olvidado tu contraseña? ",
            link = "Ayuda",
            onClick = {
                appViewModel.resetUserValues()
            }
        )
    }
}