package com.diego.chefhub.presentation.initial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diego.chefhub.R
import com.diego.chefhub.ui.theme.BackgroundButton
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.Blue
import com.diego.chefhub.ui.theme.Gray
import com.diego.chefhub.ui.theme.ShapeButton

@Composable
fun InitialScreen(navigateToLogIn: () -> Unit, navigateToSignUp: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 600f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo_no_bg),
            contentDescription = "Logo",
            modifier = Modifier.size(125.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Spacer(Modifier.height(8.dp))
        Text(
            "Millions of recipes.",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Free on ChefHub.",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.weight(1f))
        Button(
            onClick = { navigateToSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text("Sign up free", color = Black, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { navigateToLogIn() },
            Modifier.fillMaxWidth().height(48.dp).padding(horizontal =32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BackgroundButton),
            border = BorderStroke(2.dp, color = ShapeButton)
        ) {
            Text("Log In")
        }
        Spacer(Modifier.weight(1f))
    }
}