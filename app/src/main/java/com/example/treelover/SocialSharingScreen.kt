package com.example.treelover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialSharingScreen() {
    var message by remember { mutableStateOf("") }
    var sharedMessages by remember { mutableStateOf(listOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pohon), // Pastikan nama file sesuai
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(300.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Berbagi di Sosial Media",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Tulis Tips atau Pengalaman tentang Penanaman Pohon") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (message.isNotBlank()) {
                    // Tambahkan pesan ke daftar pesan yang dibagikan
                    sharedMessages = sharedMessages + message

                    // Bersihkan input pesan
                    message = ""
                }
            }) {
                Text("Bagikan")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Konten yang sudah dibagikan akan muncul di bawah ini.")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(sharedMessages) { sharedMessage ->
                    Column {
                        Text(
                            text = sharedMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    }
}
