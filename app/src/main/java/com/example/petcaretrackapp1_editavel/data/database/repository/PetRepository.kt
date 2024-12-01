package com.example.petcaretrackapp1_editavel.data.database.repository

import androidx.lifecycle.LiveData
import com.example.petcaretrackapp1_editavel.data.database.dao.PetDao
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet

class PetRepository(private val petDao: PetDao) {
    val allPets: LiveData<List<Pet>> = petDao.getAllPets()

    suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet)
    }
}
