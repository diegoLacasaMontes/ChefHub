package com.diego.chefhub.scaffold

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.diego.chefhub.R
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBackTopAppBar(navigateBack: () -> Unit) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Black, // 👈 fondo negro
            navigationIconContentColor = White, // color por defecto de iconos
            actionIconContentColor = White
        ),
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Atrás",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}