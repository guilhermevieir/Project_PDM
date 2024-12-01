package com.example.petcaretrackapp1_editavel.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "medications",
    foreignKeys = [ForeignKey(
        entity = Pet::class,
        parentColumns = ["id"],
        childColumns = ["petId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val petId: Int,
    val medicationName: String,
    val dosage: String,
    val schedule: String
)
