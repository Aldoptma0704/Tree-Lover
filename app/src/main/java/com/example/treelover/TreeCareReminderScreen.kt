package com.example.treelover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun TreeCareReminderScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Pengingat Perawatan Pohon",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        // List of reminders
        // Implementasi daftar pengingat dan notifikasi
        Button(onClick = { /* Handle add reminder */ }) {
            Text("Tambah Pengingat")
        }
    }
}
