package com.diego.chefhub.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width: Int) {
    Spacer(Modifier.width(width.dp))
}

@Composable
fun VerticalSpacer(height: Int) {
    Spacer(Modifier.height(height.dp))
}