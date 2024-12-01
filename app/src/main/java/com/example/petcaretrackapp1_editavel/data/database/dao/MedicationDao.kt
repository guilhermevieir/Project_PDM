package com.example.petcaretrackapp1_editavel.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petcaretrackapp1_editavel.data.database.entities.Medication

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medications WHERE petId = :petId")
    fun getMedicationsByPetId(petId: Int): LiveData<List<Medication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication)

    @Delete
    suspend fun deleteMedication(medication: Medication)
}
