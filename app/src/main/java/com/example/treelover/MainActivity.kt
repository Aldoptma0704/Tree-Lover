package com.example.treelover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.treelover.ui.theme.TreeLoverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreeLoverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainScreen(navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tree Lover") }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "dashboard", Modifier.padding(innerPadding)) {
            composable("dashboard") { DashboardScreen() }
            composable("calculator") { PlantingCalculatorScreen() }
            composable("tree_selection") { TreeSelectionScreen() }
            composable("social_sharing") { SocialSharingScreen() }
            composable("tree_care_reminder") { TreeCareReminderScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Dashboard") },
            selected = currentRoute == "dashboard",
            onClick = { navController.navigate("dashboard") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Calculate, contentDescription = null) },
            label = { Text("Calculator") },
            selected = currentRoute == "calculator",
            onClick = { navController.navigate("calculator") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Tree Selection") },
            selected = currentRoute == "tree_selection",
            onClick = { navController.navigate("tree_selection") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Share, contentDescription = null) },
            label = { Text("Sharing") },
            selected = currentRoute == "social_sharing",
            onClick = { navController.navigate("social_sharing") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text("Reminders") },
            selected = currentRoute == "tree_care_reminder",
            onClick = { navController.navigate("tree_care_reminder") }
        )
    }
}
