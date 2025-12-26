package com.diego.chefhub.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.diego.chefhub.R
import com.diego.chefhub.ui.theme.DarkSelectedField
import com.diego.chefhub.ui.theme.DarkUnselectedField

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onValueChangeExtra: (String) -> Unit = {},
    password: Boolean = false
) {
    var showPassword by rememberSaveable { mutableStateOf(false) }

    val visual = when {
        password && !showPassword -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            onValueChangeExtra(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (password) KeyboardType.Password else KeyboardType.Text
        ),
        visualTransformation = visual,
        trailingIcon = {
            if (password) {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ojo_ocultar),
                        contentDescription = "Mostrar/Ocultar contraseña",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = DarkUnselectedField,
            focusedContainerColor = DarkSelectedField
        ),
        modifier = Modifier.fillMaxWidth()
    )
}