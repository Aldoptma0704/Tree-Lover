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
        LazyColumn {
            items(trees) { tree ->
                TreeProfileCard(tree) { updatedTree ->
                    val index = trees.indexOfFirst { it.id == tree.id }
                    if (index != -1) {
                        trees[index] = updatedTree
                    }
                }
            }
        }
    }
}

@Composable
fun TreeProfileCard(tree: Tree, onTreeUpdate: (Tree) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(TextFieldValue(tree.name)) }
    var location by remember { mutableStateOf(TextFieldValue(tree.location)) }
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
                    if (expanded) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                                onTreeUpdate(tree.copy(name = it.text))
                            },
                            label = { Text("Nama") }
                        )
                        OutlinedTextField(
                            value = location,
                            onValueChange = {
                                location = it
                                onTreeUpdate(tree.copy(location = it.text))
                            },
                            label = { Text("Lokasi") }
                        )
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
                            label = { Text("pupuk/gr") }
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
                    } else {
                        Text(text = name.text, fontSize = 20.sp)
                        Text(text = "Lokasi: ${location.text}", fontSize = 16.sp)
                        Text(text = "Tanggal Tanam: ${plantDate.text}", fontSize = 16.sp)
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

// Function to determine the condition of the tree
fun determineTreeCondition(pupuk: String, water: String): String {
    // Example decision-making logic
    val pupukInt = pupuk.toIntOrNull() ?: 0
    val waterInt = water.toIntOrNull() ?: 0

    return when {
        pupukInt < 200  && waterInt > 2 -> "Sehat"
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
        name = "Pohon A",
        location = "Lokasi A",
        plantDate = "01-01-2020",
        currentCondition = "Sehat",
        information = "",
        imageRes = R.drawable.pohon_type_a
    ),
    Tree(
        id = 2,
        name = "Pohon B",
        location = "Lokasi B",
        plantDate = "02-02-2021",
        currentCondition = "Perlu Perhatian",
        information = "",
        imageRes = R.drawable.pohon_type_a
    )
)