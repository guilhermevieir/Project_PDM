package com.example.petcaretrackapp1_editavel.ui.theme.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petcaretrackapp1_editavel.ui.theme.viewmodels.MedicationViewModel

@Composable
fun MedicationListFragment(viewModel: MedicationViewModel = viewModel(), petId: Int) {
    val medications = viewModel.getMedicationsByPetId(petId).observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        medications.value.forEach { medication ->
            Text(
                text = "Medication: ${medication.medicationName} - ${medication.dosage} (${medication.schedule})",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
