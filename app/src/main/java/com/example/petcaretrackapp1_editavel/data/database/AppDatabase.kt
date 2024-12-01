package com.example.petcaretrackapp1_editavel.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petcaretrackapp1_editavel.data.database.dao.AppointmentDao
import com.example.petcaretrackapp1_editavel.data.database.dao.MedicationDao
import com.example.petcaretrackapp1_editavel.data.database.dao.PetDao
import com.example.petcaretrackapp1_editavel.data.database.entities.Appointment
import com.example.petcaretrackapp1_editavel.data.database.entities.Medication
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet

@Database(entities = [Pet::class, Appointment::class, Medication::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun medicationDao(): MedicationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "petcare_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}