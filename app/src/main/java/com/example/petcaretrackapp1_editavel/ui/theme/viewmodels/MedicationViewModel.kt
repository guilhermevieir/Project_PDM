package com.example.petcaretrackapp1_editavel.ui.theme.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.petcaretrackapp1_editavel.data.database.AppDatabase
import com.example.petcaretrackapp1_editavel.data.database.entities.Medication
import com.example.petcaretrackapp1_editavel.data.database.repository.MedicationRepository
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MedicationRepository

    init {
        val medicationDao = AppDatabase.getDatabase(application).medicationDao()
        repository = MedicationRepository(medicationDao)
    }

    fun getMedicationsByPetId(petId: Int): LiveData<List<Medication>> {
        return repository.getMedicationsByPetId(petId)
    }

    fun insertMedication(medication: Medication) = viewModelScope.launch {
        repository.insertMedication(medication)
    }

    fun deleteMedication(medication: Medication) = viewModelScope.launch {
        repository.deleteMedication(medication)
    }
}
