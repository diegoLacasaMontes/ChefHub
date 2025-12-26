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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.diego.chefhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBackTopAppBar(
    navigateBack: () -> Unit,
    title: String = ""
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            Spacer(modifier = Modifier.size(48.dp))
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
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
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
                    horizontalArrangement = Arrangement.Start,
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
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            ),
            actions = {
                PersonalizedIconButton(
                    modifier = Modifier.size(28.dp),
                    icon = R.drawable.ic_settings,
                    contentDescription = "Settings",
                    onClick = { navigateToSettings() }
                )
            }
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
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 0.25.dp
        )

        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
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
                        color = MaterialTheme.colorScheme.onBackground,
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
                        color = MaterialTheme.colorScheme.onBackground,
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
                        color = MaterialTheme.colorScheme.onBackground,
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
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = modifier
            )
        }
    }
}