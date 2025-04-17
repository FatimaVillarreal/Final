package com.example.otraversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme
import kotlinx.coroutines.launch

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                SettingsDrawer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val username = "Usuario"

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(username)
        }
    ) {
        SettingsScreen(
            onMenuClick = {
                scope.launch { drawerState.open() }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onMenuClick: () -> Unit) {
    var selectedDistance by remember { mutableStateOf("En kilómetros") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FOOD FAST") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            SectionTitle("Información general")
            SettingsItem("Créditos") { }
            SettingsItem("Contactar con el servicio técnico") { }
            SettingsItem("Recomendar aplicación") { }

            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle("Distancias")
            SettingsItem("En millas", selected = selectedDistance == "En millas") {
                selectedDistance = "En millas"
            }
            SettingsItem("En kilómetros", selected = selectedDistance == "En kilómetros") {
                selectedDistance = "En kilómetros"
            }

            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle("Caché")
            SettingsItem("Vaciar caché") {
                // Lógica de limpiar caché
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color(0xFFE57373),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SettingsItem(text: String, selected: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 16.sp, color = Color.DarkGray)
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF8BC34A)
            )
        }
    }
}