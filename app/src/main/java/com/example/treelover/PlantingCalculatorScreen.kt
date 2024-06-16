package com.example.treelover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantingCalculatorScreen() {
    var landArea by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }
    val treeTypes = listOf("Type A", "Type B", "Type C")
    var selectedTreeType by remember { mutableStateOf(treeTypes[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Kalkulator Penanaman",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = landArea,
            onValueChange = { landArea = it },
            label = { Text("Luas Lahan (m²)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedTreeType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Jenis Pohon") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                treeTypes.forEach { treeType ->
                    DropdownMenuItem(
                        text = { Text(treeType) },
                        onClick = {
                            selectedTreeType = treeType
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle calculation */ }) {
            Text("Hitung")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jumlah Pohon yang Diperlukan: (Output hasil perhitungan)")
    }
}
