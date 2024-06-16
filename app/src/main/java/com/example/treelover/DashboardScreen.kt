package com.example.treelover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pemantauan Pertumbuhan", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Grafik atau Ringkasan Pertumbuhan Pohon", modifier = Modifier.padding(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle update status */ }) {
            Text("Perbarui Status")
        }
    }
}
