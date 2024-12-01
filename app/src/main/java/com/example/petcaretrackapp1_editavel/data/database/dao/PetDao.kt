package com.example.petcaretrackapp1_editavel.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet

@Dao
interface PetDao {

    @Query("SELECT * FROM pets") // Certifique-se de que a tabela "pets" corresponde à anotação na classe `Pet`
    fun getAllPets(): LiveData<List<Pet>>

    @Insert
    suspend fun insertPet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)
}

