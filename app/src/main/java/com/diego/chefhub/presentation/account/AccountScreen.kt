package com.diego.chefhub.presentation.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diego.chefhub.R
import com.diego.chefhub.scaffold.MyAccountTopAppBar
import com.diego.chefhub.scaffold.MyNavigationBottomBar

@Composable
fun AccountScreen(
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToCreate: () -> Unit,
    navigateToRecipe: () -> Unit,
    navigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            MyAccountTopAppBar(
                user = "Dilamo22",
                navigateToSettings = { navigateToSettings() })
        },
        bottomBar = {
            MyNavigationBottomBar(
                screen = "Account",
                navigateToHome = navigateToHome,
                navigateToSearch = navigateToSearch,
                navigateToCreate = navigateToCreate
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            AccountContent(navigateToRecipe)
        }
    }
}

@Composable
private fun AccountContent(
    navigateToRecipe: () -> Unit
) {
    var viewing by rememberSaveable { mutableStateOf("publications") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(weight = 1f)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(weight = 3f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono_usuario_estandar),
                    contentDescription = "Profile picture",
                    modifier = Modifier.size(size = 100.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )

                Text(
                    text = "${stringResource(id = R.string.label_account_publications)}\n0",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "${stringResource(id = R.string.label_account_followers)}\n0",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "${stringResource(id = R.string.label_account_following)}\n0",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth()
            ) {
                PersonalizedIconButton(
                    icon = R.drawable.ic_publications,
                    contentDescription = "Publications",
                    condition = viewing.equals("publications"),
                    onClick = { viewing = "publications" }
                )

                PersonalizedIconButton(
                    icon = R.drawable.ic_save,
                    contentDescription = "Saved",
                    condition = viewing.equals("saved"),
                    onClick = { viewing = "saved" }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(weight = 2f)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            if (viewing == "publications") {
                Text(text = "Publications", color = MaterialTheme.colorScheme.onBackground)
            } else {
                Text(text = "Saved", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

@Composable
private fun PersonalizedIconButton(
    icon: Int,
    contentDescription: String,
    condition: Boolean = false,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        val iconColor = if (condition) MaterialTheme.colorScheme.onBackground else Color.DarkGray
        val iconScale = if (condition) 1f else 0.8f

        Box(
            modifier = Modifier.size(size = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = iconColor,
                modifier = Modifier.scale(iconScale)
            )
        }
    }
}