package com.example.treelover

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialSharingScreen() {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Berbagi di Sosial Media",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Tulis Tips atau Pengalaman") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle sharing */ }) {
            Text("Bagikan")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Konten yang sudah dibagikan akan muncul di bawah ini.")
        // List of shared posts
    }
}
