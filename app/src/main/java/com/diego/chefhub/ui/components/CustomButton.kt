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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diego.chefhub.ui.theme.BackgroundButton
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.Blue
import com.diego.chefhub.ui.theme.ShapeButton
import com.diego.chefhub.ui.theme.White

@Composable
fun CustomButton(
    onClick: () -> Unit,
    title: String,
    image: Int,
    transparent: Boolean,
    enabled: Boolean = true
) {
    val backgroundColor = if (transparent) BackgroundButton else Blue
    val borderColor = if (transparent) ShapeButton else Blue
    val textColor = if (transparent) White else Black

    val disabledBackground = BackgroundButton.copy(alpha = 0.3f)
    val disabledBorder = ShapeButton.copy(alpha = 0.3f)
    val disabledText = White.copy(alpha = 0.3f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp)
            .clip(CircleShape)
            .background(if (enabled) backgroundColor else disabledBackground)
            .border(2.dp, if (enabled) borderColor else disabledBorder, CircleShape)
            .clickable { if (enabled) onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(16.dp)
        )

        Text(
            text = title,
            color = if (enabled) textColor else disabledText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}