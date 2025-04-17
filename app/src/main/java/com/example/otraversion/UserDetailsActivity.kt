package com.example.otraversion

import android.app.Activity
import android.content.Intent
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

class UserDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: "Invitado"
        setContent {
            OtraversionTheme {
                UserDetailsScreen(initialName = username)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(initialName: String) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(initialName) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FOOD FAST") },
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
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Nombre actual: $initialName",
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val resultIntent = Intent().apply {
                        putExtra("updatedName", name)
                    }
                    (context as? Activity)?.setResult(Activity.RESULT_OK, resultIntent)
                    (context as? Activity)?.finish()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("GUARDAR CAMBIOS")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    Toast.makeText(context, "Cuenta eliminada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity2::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }
            ) {
                Text(
                    text = "BORRAR MI CUENTA",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
            }
        }
    }
}

