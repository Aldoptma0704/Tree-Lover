package com.example.treelover

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun TreeCareReminderScreen() {
    var reminders by remember { mutableStateOf(listOf("Siram pohon", "Pupuk tanah", "Cek kesehatan pohon")) }
    var newReminderText by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pengingat Perawatan Pohon",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Daftar pengingat
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(reminders) { reminder ->
                ReminderItem(reminder = reminder)
            }
        }

        // Input baru dan tombol tambah pengingat
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = newReminderText,
                onValueChange = { newReminderText = it },
                label = { Text("Tambah Pengingat Baru") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = {
                    if (newReminderText.isNotBlank()) {
                        reminders = reminders + newReminderText
                        newReminderText = ""
                        showSnackbar = true // Tampilkan Snackbar setelah menambahkan pengingat
                    }
                }
            ) {
                Text("Tambah")
            }
        }

        // Snackbar untuk notifikasi penambahan pengingat
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Pengingat \"$newReminderText\" ditambahkan!")
            }
            LaunchedEffect(Unit) {
                // Setelah Snackbar ditampilkan, atur kembali nilai showSnackbar menjadi false setelah beberapa detik
                delay(3000) // Tunggu 3 detik
                showSnackbar = false
            }
        }
    }
}

@Composable
fun ReminderItem(reminder: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "Reminder Icon",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = reminder,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}
