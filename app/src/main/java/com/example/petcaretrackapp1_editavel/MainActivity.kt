package com.example.petcaretrackapp1_editavel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petcaretrackapp1_editavel.data.database.entities.Pet
import com.example.petcaretrackapp1_editavel.ui.theme.Petcaretrackerapp1Theme
import com.example.petcaretrackapp1_editavel.ui.theme.viewmodels.PetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Petcaretrackerapp1Theme {
                MainApp()
            }
        }
    }
}

enum class SortType {
    NAME,
    AGE
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf("Home") } // Estado para controlar a navegação

    when (currentScreen) {
        "Home" -> HomeScreen(onNavigateToPets = { currentScreen = "PetCare" })
        "PetCare" -> PetCareScreen(onNavigateBack = { currentScreen = "Home" })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToPets: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pet Care Tracker") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Bem-vindo ao Pet Care Tracker!", style = MaterialTheme.typography.titleLarge)
                Button(onClick = onNavigateToPets) {
                    Text("Gerenciar Pets")
                }
                Button(onClick = { /* Placeholder para outra funcionalidade */ }) {
                    Text("Agendamentos (Em breve)")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetCareScreen(
    onNavigateBack: () -> Unit,
    viewModel: PetViewModel = viewModel()
) {
    val pets = viewModel.allPets.observeAsState(emptyList()) // Observar a lista de pets
    var showDialog by remember { mutableStateOf(false) } // Controle do diálogo
    var sortType by remember { mutableStateOf(SortType.NAME) } // Tipo de ordenação

    // Ordenar a lista de pets com base no tipo selecionado
    val sortedPets = when (sortType) {
        SortType.NAME -> pets.value.sortedBy { it.name }
        SortType.AGE -> pets.value.sortedBy { it.age }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gerenciar Pets") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    SortDropdownMenu(sortType = sortType, onSortChange = { sortType = it })
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Pet"
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column {
                // Exibir a lista de pets ordenada
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(sortedPets) { pet ->
                        PetItem(pet = pet) {
                            viewModel.deletePet(pet)
                        }
                    }
                }

                // Mostrar a interface de adicionar pet
                if (showDialog) {
                    AddPetDialog(
                        onDismiss = { showDialog = false },
                        onAddPet = { pet ->
                            viewModel.insertPet(pet) // Inserir pet no banco de dados
                            showDialog = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text("Nome: ${pet.name}")
                Text("Espécie: ${pet.species}")
                Text("Raça: ${pet.breed}")
                Text("Idade: ${pet.age} anos")
            }
            pet.photoUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Foto do Pet",
                    modifier = Modifier.size(64.dp)
                )
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Deletar Pet")
            }
        }
    }
}

@Composable
fun SortDropdownMenu(sortType: SortType, onSortChange: (SortType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        Button(onClick = { expanded = true }) {
            Text("Ordenar por: ${sortType.name}")
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Nome") },
                onClick = {
                    onSortChange(SortType.NAME)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Idade") },
                onClick = {
                    onSortChange(SortType.AGE)
                    expanded = false
                }
            )
        }
    }
}
@Composable
fun AddPetDialog(
    onDismiss: () -> Unit,
    onAddPet: (Pet) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> photoUri = uri?.toString() }
    )

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Adicionar Pet") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome do Pet") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = species,
                    onValueChange = { species = it },
                    label = { Text("Espécie do Pet") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = breed,
                    onValueChange = { breed = it },
                    label = { Text("Raça do Pet") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Idade do Pet") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Selecionar Foto")
                }

                if (photoUri != null) {
                    Text("Foto selecionada: $photoUri", style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotEmpty() && species.isNotEmpty() && breed.isNotEmpty() && age.isNotEmpty()) {
                    onAddPet(
                        Pet(
                            name = name,
                            species = species,
                            breed = breed,
                            age = age.toInt(),
                            photoUri = photoUri
                        )
                    )
                    onDismiss()
                }
            }) {
                Text("Adicionar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}



