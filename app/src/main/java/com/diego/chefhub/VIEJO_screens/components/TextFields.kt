package com.diego.chefhub.VIEJO_screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diego.chefhub.R

@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    required: Boolean = false
) {
    // COMENTARIO
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Label(text = label, required = required) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    required: Boolean
) {
    // COMENTARIO.
    var showPassword by rememberSaveable { mutableStateOf(false) }

    // COMENTARIO.
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Label(text = label, required = required) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation =
            if (showPassword) {
                // COMENTARIO.
                VisualTransformation.None
            } else {
                // COMENTARIO.
                PasswordVisualTransformation()
            },
        trailingIcon = {
            // COMENTARIO.
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    painter = painterResource(id = R.drawable.ojo_ocultar),
                    contentDescription = "Mostrar/Ocultar contraseña",
                    modifier = Modifier.size(50.dp),
                )
            }
        }
    )
}

@Composable
private fun Label(
    text: String,
    required: Boolean = false
) {
    // COMENTARIO.
    Row {
        Text(text = text)
        // COMENTARIO.
        if (required) {
            Text(
                text = "*",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 2.dp)
            )
        }
    }
}