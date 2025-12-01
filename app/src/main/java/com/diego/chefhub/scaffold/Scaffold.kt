package com.diego.chefhub.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.diego.chefhub.R
import com.diego.chefhub.ui.theme.Black
import com.diego.chefhub.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBackTopAppBar(
    navigateBack: () -> Unit,
    title: String = ""
) {
    TopAppBar(
        title = { Text(title, color = White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Black,
            navigationIconContentColor = White,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHomeTopAppBar() {
    Column {
        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "ChefHub",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Black,
                titleContentColor = White
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountTopAppBar(
    user: String,
    navigateToSettings: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = user,
                        fontWeight = FontWeight.Bold
                    )

                    PersonalizedIconButton(
                        modifier = Modifier.size(16.dp),
                        icon = R.drawable.ic_arrow_down,
                        contentDescription = "Down Arrow",
                        onClick = { /* TODO */ }
                    )

                    Spacer(Modifier.weight(1f))

                    PersonalizedIconButton(
                        modifier = Modifier.size(28.dp),
                        icon = R.drawable.ic_settings,
                        contentDescription = "Settings",
                        onClick = { navigateToSettings() }
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Black,
                titleContentColor = White
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationBottomBar(
    screen: String,
    navigateToHome: () -> Unit = {},
    navigateToSearch: () -> Unit = {},
    navigateToCreate: () -> Unit = {},
    navigateToAccount: () -> Unit = {}
) {
    Column {
        HorizontalDivider(
            color = White,
            thickness = 0.25.dp
        )

        BottomAppBar(
            containerColor = Black,
            contentColor = White,
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PersonalizedIconButton(
                        icon = R.drawable.ic_home,
                        contentDescription = "Home",
                        condition = screen == "Home",
                        onClick = { navigateToHome() }
                    )

                    VerticalDivider(
                        color = White,
                        thickness = 1.dp,
                        modifier = Modifier.height(24.dp)
                    )

                    PersonalizedIconButton(
                        icon = R.drawable.ic_search,
                        contentDescription = "Search",
                        condition = screen == "Search",
                        onClick = { navigateToSearch() }
                    )

                    VerticalDivider(
                        color = White,
                        thickness = 1.dp,
                        modifier = Modifier.height(24.dp)
                    )

                    PersonalizedIconButton(
                        icon = R.drawable.ic_add,
                        contentDescription = "Create",
                        condition = screen == "Create",
                        onClick = { navigateToCreate() }
                    )

                    VerticalDivider(
                        color = White,
                        thickness = 1.dp,
                        modifier = Modifier.height(24.dp)
                    )

                    PersonalizedIconButton(
                        icon = R.drawable.ic_account,
                        contentDescription = "Account",
                        condition = screen == "Account",
                        onClick = { navigateToAccount() }
                    )
                }
            }
        )
    }
}

@Composable
private fun PersonalizedIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    contentDescription: String,
    condition: Boolean = false,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        val backgroundColor = if (condition) Color(0xFF00796B) else Color.Transparent

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(backgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = White,
                modifier = modifier
            )
        }
    }
}