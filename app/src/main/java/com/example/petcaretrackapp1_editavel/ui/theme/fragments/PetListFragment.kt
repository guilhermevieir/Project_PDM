package com.example.petcaretrackapp1_editavel.ui.theme.fragments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet
import com.example.petcaretrackapp1_editavel.ui.theme.viewmodels.PetViewModel
import androidx.compose.material3.CardDefaults

@Composable
fun PetListFragment(viewModel: PetViewModel = viewModel()) {
    val pets = viewModel.allPets.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(pets.value) { pet ->
            PetItem(pet = pet, onClick = { /* Navegar para detalhes */ })
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Usando CardDefaults para elevação
    ) {
        Text(
            text = "${pet.name} - ${pet.species}",
            modifier = Modifier.padding(16.dp)
        )
    }
}
