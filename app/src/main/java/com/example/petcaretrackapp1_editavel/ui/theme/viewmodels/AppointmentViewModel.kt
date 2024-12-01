package com.example.petcaretrackapp1_editavel.ui.theme.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.petcaretrackapp1_editavel.data.database.AppDatabase
import com.example.petcaretrackapp1_editavel.data.database.entities.Appointment
import com.example.petcaretrackapp1_editavel.data.database.repository.AppointmentRepository
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppointmentRepository

    init {
        val appointmentDao = AppDatabase.getDatabase(application).appointmentDao()
        repository = AppointmentRepository(appointmentDao)
    }

    fun getAppointmentsByPetId(petId: Int): LiveData<List<Appointment>> {
        return repository.getAppointmentsByPetId(petId)
    }

    fun insertAppointment(appointment: Appointment) = viewModelScope.launch {
        repository.insertAppointment(appointment)
    }

    fun deleteAppointment(appointment: Appointment) = viewModelScope.launch {
        repository.deleteAppointment(appointment)
    }
}
