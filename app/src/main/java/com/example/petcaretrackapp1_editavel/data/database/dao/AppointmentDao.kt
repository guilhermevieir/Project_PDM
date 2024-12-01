package com.example.petcaretrackapp1_editavel.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petcaretrackapp1_editavel.data.database.entities.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments WHERE petId = :petId")
    fun getAppointmentsByPetId(petId: Int): LiveData<List<Appointment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)
}
