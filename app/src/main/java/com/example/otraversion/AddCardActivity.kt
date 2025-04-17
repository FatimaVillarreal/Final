package com.example.otraversion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

class AddCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                AddCardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen() {
    val context = LocalContext.current

    var cardNumber by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    val isFormValid = cardNumber.isNotBlank() && cardHolder.isNotBlank() && month.isNotBlank() && year.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MIS TARJETAS") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { (context as? Activity)?.finish() }) {
                        Text("X", fontWeight = FontWeight.Bold, color = Color.Gray)
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = { Text("Número de tarjeta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = cardHolder,
                onValueChange = { cardHolder = it },
                label = { Text("Nombre del titular de la tarjeta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = month,
                    onValueChange = { month = it },
                    label = { Text("MM") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("AAAA") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val formatted = "Visa\n**** **** **** ${cardNumber.takeLast(4)}\n$month/$year"
                    val resultIntent = Intent().apply {
                        putExtra("newCard", formatted)
                    }
                    (context as? Activity)?.setResult(Activity.RESULT_OK, resultIntent)
                    (context as? Activity)?.finish()
                },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE09B)),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("AÑADIR UNA TARJETA", fontWeight = FontWeight.Bold)
            }
        }
    }
}
