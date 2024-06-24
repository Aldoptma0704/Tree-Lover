package com.example.treelover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Define a data class for tree details
data class TreeDetail(
    val type: String,
    val name: String,
    val location: String,
    val suitableSeason: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeSelectionScreen() {
    // Define tree details for each type
    val treeDetails = listOf(
        TreeDetail("Type A", "Pohon Pinus", "Pegunungan", "Musim yang cocok untuk menanam pohon ini: Musim Semi"),
        TreeDetail("Type A", "Pohon Cemara", "Dataran Tinggi", "Musim yang cocok untuk menanam pohon ini: Musim Panas"),
        TreeDetail("Type A", "Pohon Eucalyptus", "Lereng Gunung", "Musim yang cocok untuk menanam pohon ini: Musim Gugur"),

        TreeDetail("Type B", "Pohon Mangga", "Kebun Buah", "Musim yang cocok untuk menanam pohon ini: Musim Semi"),
        TreeDetail("Type B", "Pohon Jambu", "Kebun Belakang", "Musim yang cocok untuk menanam pohon ini: Musim Panas"),
        TreeDetail("Type B", "Pohon Pisang", "Lahan Pertanian", "Musim yang cocok untuk menanam pohon ini: Musim Gugur"),

        TreeDetail("Type C", "Pohon Kelapa", "Pantai", "Musim yang cocok untuk menanam pohon ini: Musim Panas"),
        TreeDetail("Type C", "Pohon Bakau", "Pesisir", "Musim yang cocok untuk menanam pohon ini: Musim Gugur"),
        TreeDetail("Type C", "Pohon Pandan Laut", "Garis Pantai", "Musim yang cocok untuk menanam pohon ini: Musim Semi")
    )

    val treeTypes = treeDetails.map { it.type }.distinct()
    var selectedTreeType by remember { mutableStateOf(treeTypes[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Pemilihan Jenis Pohon", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedTreeType,
                onValueChange = { },
                readOnly = true,
                label = { Text("Pilih Jenis Pohon") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                treeTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedTreeType = type
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Display the details of the selected tree type
        Text(text = "Details for $selectedTreeType", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        // Filter and display trees of the selected type
        val filteredTrees = treeDetails.filter { it.type == selectedTreeType }
        filteredTrees.forEach { tree ->
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(text = "Nama: ${tree.name}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Lokasi: ${tree.location}", style = MaterialTheme.typography.bodyLarge)
                Text(text = tree.suitableSeason, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
