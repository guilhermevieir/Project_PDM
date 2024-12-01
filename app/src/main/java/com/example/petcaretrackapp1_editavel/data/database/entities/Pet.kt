package com.example.petcaretrackapp1_editavel.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val photoUri: String? = null // Adiciona o URI da foto
)

