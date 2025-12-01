package com.diego.chefhub.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diego.chefhub.R
import com.diego.chefhub.scaffold.MyBackTopAppBar
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.Dark_White
import com.diego.chefhub.ui.theme.Gray
import com.diego.chefhub.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(
    navigateToAccount: () -> Unit,
    auth: FirebaseAuth,
    navigateToInitial: () -> Unit
) {
    Scaffold(
        topBar = { MyBackTopAppBar(navigateBack = { navigateToAccount() }, title = "Settings") }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            SettingsContent(auth, navigateToInitial)
        }
    }
}

@Composable
private fun SettingsContent(
    auth: FirebaseAuth,
    navigateToInitial: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Black)
    ) {
        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            "Account Settings",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton(title = "Edit profile", onClick = {}, icon = R.drawable.ic_edit_profile)
        CustomButton("Change password", onClick = {}, icon = R.drawable.ic_shield)
        CustomButton("Log out", onClick = {
            auth.signOut()
            navigateToInitial()
        }, icon = R.drawable.ic_log_out)
        CustomButton("Delete account", onClick = {
            auth.currentUser?.delete()
            navigateToInitial()
        }, icon = R.drawable.ic_delete_user)
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            "Notifications",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton("Receive notifications", onClick = {}, icon = R.drawable.ic_notifications)
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            "Privacy",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton("Change privacy", onClick = {}, icon = R.drawable.ic_privacy)
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            "Comments and Support",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton("See comments", onClick = {}, icon = R.drawable.ic_comments)
        CustomButton("Contact with support", onClick = {}, icon = R.drawable.ic_support)
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            "Accessibility",
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        CustomButton("Dark mode", onClick = {}, icon = R.drawable.ic_dark_mode)
        CustomButton("Change language", onClick = {}, icon = R.drawable.ic_language)
        CustomButton("Font size", onClick = {}, icon = R.drawable.ic_font_size)
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)
    }
}

@Composable
private fun CustomButton(title: String, onClick: () -> Unit, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Dark_White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = title,
            color = Dark_White,
            fontSize = 20.sp
        )

        Text(
            text = ">",
            color = Dark_White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}