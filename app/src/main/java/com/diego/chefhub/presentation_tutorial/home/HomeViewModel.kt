package com.diego.chefhub.presentation_tutorial.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.chefhub.presentation_tutorial.model.Artist
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {
    private lateinit var db: FirebaseFirestore

    private val _artist = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artist

    init {
        db = Firebase.firestore

        getArtists()
    }

    private fun getArtists() {
        viewModelScope.launch {
            val result: List<Artist> = withContext(Dispatchers.IO) {
                getAllArtists()
            }
            _artist.value = result
        }
    }

    suspend fun getAllArtists():List<Artist> {
        return try {
            db.collection("artists")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Artist::class.java)
                }
        } catch (e: Exception) {
            Log.i("diego", e.toString())
            emptyList()
        }
    }
}