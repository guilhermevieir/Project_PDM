package com.example.petcaretrackapp1_editavel.ui.theme.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.petcaretrackapp1_editavel.data.database.PetDatabase
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet
import com.example.petcaretrackapp1_editavel.data.database.repository.PetRepository
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PetRepository
    val allPets: LiveData<List<Pet>>

    init {
        val petDao = PetDatabase.getDatabase(application).petDao()
        repository = PetRepository(petDao)
        allPets = repository.allPets
    }

    fun insertPet(pet: Pet) = viewModelScope.launch {
        repository.insertPet(pet)
    }

    fun deletePet(pet: Pet) = viewModelScope.launch {
        repository.deletePet(pet)
    }
}
