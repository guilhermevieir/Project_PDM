package com.example.petcaretrackapp1_editavel.data.database.repository

import androidx.lifecycle.LiveData
import com.example.petcaretrackapp1_editavel.data.database.dao.AppointmentDao
import com.example.petcaretrackapp1_editavel.data.database.entities.Appointment

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    fun getAppointmentsByPetId(petId: Int): LiveData<List<Appointment>> {
        return appointmentDao.getAppointmentsByPetId(petId)
    }

    suspend fun insertAppointment(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment)
    }

    suspend fun deleteAppointment(appointment: Appointment) {
        appointmentDao.deleteAppointment(appointment)
    }
}
