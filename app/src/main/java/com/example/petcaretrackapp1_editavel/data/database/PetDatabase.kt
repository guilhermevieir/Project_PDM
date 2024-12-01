package com.example.petcaretrackapp1_editavel.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petcaretrackapp1_editavel.data.database.dao.PetDao
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: PetDatabase? = null

        fun getDatabase(context: Context): PetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetDatabase::class.java,
                    "petcare_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

