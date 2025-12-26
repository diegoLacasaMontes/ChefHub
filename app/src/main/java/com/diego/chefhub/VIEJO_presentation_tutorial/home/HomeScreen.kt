package com.diego.chefhub.VIEJO_presentation_tutorial.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.diego.chefhub.VIEJO_presentation_tutorial.model.Artist
import com.diego.chefhub.ui.theme.DarkBackground
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreenViejo(auth: FirebaseAuth, navigateToInitial: () -> Unit, viewModel: HomeViewModel = HomeViewModel()) {
    val artists = viewModel.artist.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Text(
            "Popular artist",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(artists.value) {
                ArtistItem(it)
            }
        }

        Spacer(Modifier.height(48.dp))

        Button(onClick = {
            auth.signOut()
            navigateToInitial()
        }) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun ArtistItem(artist: Artist) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = artist.image,
            contentDescription = "Artist image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(), color = Color.White)
    }
}