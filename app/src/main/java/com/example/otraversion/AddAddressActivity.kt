package com.example.otraversion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme

class AddAddressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                AddAddressScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen() {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var address2 by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    var showNameError by remember { mutableStateOf(false) }
    var showSurnameError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NUEVA DIRECCIÓN") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    showNameError = it.isBlank()
                },
                label = { Text("Nombre*") },
                isError = showNameError,
                modifier = Modifier.fillMaxWidth()
            )
            if (showNameError) Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = surname,
                onValueChange = {
                    surname = it
                    showSurnameError = it.isBlank()
                },
                label = { Text("Apellido*") },
                isError = showSurnameError,
                modifier = Modifier.fillMaxWidth()
            )
            if (showSurnameError) Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address2,
                onValueChange = { address2 = it },
                label = { Text("Línea de dirección 2") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = { postalCode = it },
                    label = { Text("Código p.") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Ciudad*") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("País*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    showNameError = name.isBlank()
                    showSurnameError = surname.isBlank()

                    if (!showNameError && !showSurnameError) {
                        Toast.makeText(context, "Dirección guardada", Toast.LENGTH_SHORT).show()
                        // Aquí se puede volver a la pantalla anterior o guardar en BD
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE09B)),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("GUARDAR", fontWeight = FontWeight.Bold)
            }
        }
    }
}
