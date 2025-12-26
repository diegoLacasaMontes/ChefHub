package com.diego.chefhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    title: String,
    image: Int? = null,
    transparent: Boolean = false,
    enabled: Boolean = true
) {
    val borderColor = if (transparent) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background
    val backgroundColor = if (transparent) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
    val textColor = if (transparent) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onPrimary

    val disabledBorder = borderColor.copy(alpha = 0.3f)
    val disabledBackground = backgroundColor.copy(alpha = 0.3f)
    val disabledText = textColor.copy(alpha = 0.3f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .padding(horizontal = 32.dp)
            .clip(CircleShape)
            .background(color = if (enabled) backgroundColor else disabledBackground)
            .border(width = 2.dp, color = if (enabled) borderColor else disabledBorder, CircleShape)
            .clickable { if (enabled) onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(16.dp)
            )
        }

        Text(
            text = title,
            color = if (enabled) textColor else disabledText,
            fontWeight = FontWeight.Bold
        )
    }
}