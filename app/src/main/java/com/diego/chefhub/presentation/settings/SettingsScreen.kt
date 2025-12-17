package com.diego.chefhub.presentation.settings

import android.util.Log
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diego.chefhub.R
import com.diego.chefhub.scaffold.MyBackTopAppBar
import com.diego.chefhub.ui.AppViewModel
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.Dark_White
import com.diego.chefhub.ui.theme.Gray
import com.diego.chefhub.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(
    navigateToAccount: () -> Unit,
    navigateToInitial: () -> Unit,
    auth: FirebaseAuth
) {
    val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(auth))
    val appViewModel: AppViewModel = viewModel()
    val appUiState by appViewModel.appUiState.collectAsState()

    Scaffold(
        topBar = {
            MyBackTopAppBar(
                navigateBack = { navigateToAccount() },
                title = stringResource(id = R.string.title_settings)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
        ) {
            SettingsContent(
                onLogout = { settingsViewModel.logout(navigateToInitial) },
                onDeleteAccount = {
                    settingsViewModel.deleteAccount(
                        onSuccess = navigateToInitial,
                        onError = { error -> Log.e("Settings", error) }
                    )
                },
                selectedTheme = appUiState.selectedTheme,
                themeOptions = appUiState.themeOptions,
                onChangeTheme = { newTheme ->
                    appViewModel.changeTheme(newTheme)
                },
                selectedLanguage = appUiState.selectedLanguage,
                languageOptions = appUiState.languageOptions.keys.toList(),
                onChangeLanguage = { newLanguage ->
                    appViewModel.changeLanguage(newLanguage)
                }
            )
        }
    }
}

@Composable
private fun SettingsContent(
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit,
    selectedTheme: String,
    themeOptions: List<String>,
    onChangeTheme: (String) -> Unit,
    selectedLanguage: String,
    languageOptions: List<String>,
    onChangeLanguage: (String) -> Unit
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
            text = stringResource(id = R.string.section_settings_account),
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        CustomButton(
            title = stringResource(id = R.string.action_edit_profile),
            icon = R.drawable.ic_edit_profile,
            onClick = {})
        CustomButton(
            title = stringResource(id = R.string.action_change_password),
            icon = R.drawable.ic_shield,
            onClick = {})
        CustomButton(
            title = stringResource(id = R.string.action_logout),
            icon = R.drawable.ic_log_out,
            onClick = onLogout
        )
        CustomButton(
            title = stringResource(id = R.string.action_delete_account),
            icon = R.drawable.ic_delete_user,
            onClick = onDeleteAccount
        )
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            text = stringResource(id = R.string.section_settings_notifications),
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        CustomButton(
            title = stringResource(id = R.string.pref_receive_notifications),
            icon = R.drawable.ic_notifications,
            onClick = {})
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            text = stringResource(id = R.string.section_settings_privacy),
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        CustomButton(
            title = stringResource(id = R.string.action_change_privacy),
            icon = R.drawable.ic_privacy,
            onClick = {})
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            text = stringResource(id = R.string.section_settings_support),
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        CustomButton(
            title = stringResource(id = R.string.action_view_comments),
            icon = R.drawable.ic_comments,
            onClick = {})
        CustomButton(
            title = stringResource(id = R.string.action_contact_support),
            icon = R.drawable.ic_support,
            onClick = {})
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)

        Text(
            text = stringResource(id = R.string.section_settings_accessibility),
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        SettingsDropdownItem(
            title = stringResource(id = R.string.pref_language),
            icon = R.drawable.ic_dark_mode,
            selectedOption = selectedTheme,
            options = themeOptions,
            onOptionSelected = onChangeTheme
        )
        SettingsDropdownItem(
            title = stringResource(id = R.string.pref_theme),
            icon = R.drawable.ic_language,
            selectedOption = selectedLanguage,
            options = languageOptions,
            onOptionSelected = onChangeLanguage
        )
        CustomButton(
            title = stringResource(id = R.string.pref_font_size),
            icon = R.drawable.ic_font_size,
            onClick = {})
        Spacer(Modifier.height(24.dp))

        HorizontalDivider(color = Gray, thickness = 5.dp)
    }
}

@Composable
private fun CustomButton(title: String, icon: Int, onClick: () -> Unit) {
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

@Composable
fun SettingsDropdownItem(
    title: String,
    icon: Int,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        CustomButton(
            title = "$title: $selectedOption",
            icon = icon,
            onClick = { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontWeight = if (option == selectedOption) FontWeight.Bold else FontWeight.Normal,
                            color = Black
                        )
                    },
                    onClick = {
                        expanded = false
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}