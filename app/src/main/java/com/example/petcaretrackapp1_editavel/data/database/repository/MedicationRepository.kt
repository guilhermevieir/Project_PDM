package com.example.petcaretrackapp1_editavel.data.database.repository

import androidx.lifecycle.LiveData
import com.example.petcaretrackapp1_editavel.data.database.dao.MedicationDao
import com.example.petcaretrackapp1_editavel.data.database.entities.Medication

class MedicationRepository(private val medicationDao: MedicationDao) {
    fun getMedicationsByPetId(petId: Int): LiveData<List<Medication>> {
        return medicationDao.getMedicationsByPetId(petId)
    }

    suspend fun insertMedication(medication: Medication) {
        medicationDao.insertMedication(medication)
    }

    suspend fun deleteMedication(medication: Medication) {
        medicationDao.deleteMedication(medication)
    }
}