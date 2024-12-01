package com.example.petcaretrackapp1_editavel.ui.theme.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petcaretrackapp1_editavel.ui.theme.viewmodels.AppointmentViewModel


@Composable
fun AppointmentListFragment(viewModel: AppointmentViewModel = viewModel(), petId: Int) {
    val appointments = viewModel.getAppointmentsByPetId(petId).observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        appointments.value.forEach { appointment ->
            Text(text = "Appointment: ${appointment.type} on ${appointment.date}")
        }
    }
}