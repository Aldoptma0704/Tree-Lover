package com.example.treelover

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.treelover.ui.theme.TreeLoverTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    // Define a mutable state list of trees
    val trees = remember { mutableStateListOf(*sampleTrees.toTypedArray()) }
    var showAddTreeDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Pemantauan Pertumbuhan",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of Trees
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(trees) { tree ->
                TreeProfileCard(tree) { updatedTree ->
                    val index = trees.indexOfFirst { it.id == tree.id }
                    if (index != -1) {
                        trees[index] = updatedTree
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showAddTreeDialog = true }) {
            Text("Tambah Pohon")
        }

        if (showAddTreeDialog) {
            AddTreeDialog(
                onAddTree = { newTree ->
                    trees.add(newTree)
                    showAddTreeDialog = false
                },
                onDismiss = { showAddTreeDialog = false }
            )
        }
    }
}

@Composable
fun TreeProfileCard(tree: Tree, onTreeUpdate: (Tree) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var plantDate by remember { mutableStateOf(TextFieldValue(tree.plantDate)) }
    var sunlight by remember { mutableStateOf(TextFieldValue("")) }
    var water by remember { mutableStateOf(TextFieldValue("")) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = tree.imageRes),
                    contentDescription = "Tree Image",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = tree.name, fontSize = 20.sp)
                    Text(text = "Lokasi: ${tree.location}", fontSize = 16.sp)
                    if (expanded) {
                        OutlinedTextField(
                            value = plantDate,
                            onValueChange = {
                                plantDate = it
                                onTreeUpdate(tree.copy(plantDate = it.text))
                            },
                            label = { Text("Tanggal Tanam") }
                        )
                        OutlinedTextField(
                            value = sunlight,
                            onValueChange = {
                                sunlight = it
                            },
                            label = { Text("Pupuk/gr") }
                        )
                        OutlinedTextField(
                            value = water,
                            onValueChange = {
                                water = it
                            },
                            label = { Text("Kebutuhan Air/L") }
                        )
                        Button(onClick = {
                            val condition = determineTreeCondition(sunlight.text, water.text)
                            onTreeUpdate(tree.copy(currentCondition = condition))
                        }) {
                            Text("Update Kondisi")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Kondisi Saat Ini: ${tree.currentCondition}")
            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Informasi tambahan: ${tree.information}")
            }
        }
    }
}

@Composable
fun AddTreeDialog(onAddTree: (Tree) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var location by remember { mutableStateOf(TextFieldValue("")) }
    var plantDate by remember { mutableStateOf(TextFieldValue("")) }
    var fertilizer by remember { mutableStateOf(TextFieldValue("")) }
    var water by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                val newTree = Tree(
                    id = (Math.random() * 1000).toInt(), // Generate a random ID for simplicity
                    name = name.text,
                    location = location.text,
                    plantDate = plantDate.text,
                    currentCondition = "Belum Diketahui",
                    information = "Informasi baru",
                    imageRes = R.drawable.pohon_type_a
                )
                onAddTree(newTree)
            }) {
                Text("Tambah")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Batal")
            }
        },
        title = {
            Text(text = "Tambah Pohon")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Pohon") }
                )
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Lokasi") }
                )
                OutlinedTextField(
                    value = plantDate,
                    onValueChange = { plantDate = it },
                    label = { Text("Tanggal Tanam") }
                )
                OutlinedTextField(
                    value = fertilizer,
                    onValueChange = { fertilizer = it },
                    label = { Text("Pupuk/gr") }
                )
                OutlinedTextField(
                    value = water,
                    onValueChange = { water = it },
                    label = { Text("Kebutuhan Air/L") }
                )
            }
        }
    )
}

// Function to determine the condition of the tree
fun determineTreeCondition(pupuk: String, water: String): String {
    // Example decision-making logic
    val pupukInt = pupuk.toIntOrNull() ?: 0
    val waterInt = water.toIntOrNull() ?: 0

    return when {
        pupukInt < 200 && waterInt > 2 -> "Sehat"
        pupukInt < 50 || waterInt < 1 -> "Perlu Perhatian"
        else -> "Tingkatkan Air dan Pupuk"
    }
}

// Sample data class for Tree
data class Tree(
    val id: Int,
    val name: String,
    val location: String,
    val plantDate: String,
    val currentCondition: String,
    val information: String,
    val imageRes: Int
)

// Sample tree data
val sampleTrees = listOf(
    Tree(
        id = 1,
        name = "Pohon Mangga",
        location = "Kebun Buah",
        plantDate = "01-01-2020",
        currentCondition = "Sehat",
        information = "Pohon Mangga Madu",
        imageRes = R.drawable.pohon_type_a
    ),
    Tree(
        id = 2,
        name = "Pohon Kelapa",
        location = "Pantai",
        plantDate = "02-02-2021",
        currentCondition = "Perlu Perhatian",
        information = "Pohon Kelapa Hibrida",
        imageRes = R.drawable.pohon_type_a
    )
)
