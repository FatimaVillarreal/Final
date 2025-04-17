package com.example.otraversion

import android.app.Activity
import android.content.Intent
import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

class CheckoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val total = intent.getDoubleExtra("total", 0.0)
        val productCount = intent.getIntExtra("count", 0)
        val username = intent.getStringExtra("username") ?: "Usuario"

        setContent {
            OtraversionTheme {
                CheckoutScreen(total, productCount, username)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(total: Double, count: Int, username: String) {
    val context = LocalContext.current
    var selectedAddress by remember { mutableStateOf(username) }

    val addressLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val address = result.data?.getStringExtra("selectedAddress")
            if (!address.isNullOrEmpty()) {
                selectedAddress = address
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FOOD FAST") },
                navigationIcon = {
                    IconButton(onClick = { (context as? Activity)?.finish() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Tu Pedido", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total a Pagar de Productos")
                Text("$${"%.2f".format(total)}")
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Envío")
                Text("$N")
            }
            Divider()
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total a Pagar", fontWeight = FontWeight.Bold)
                Text("$${"%.2f".format(total)}", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tus Detalles", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Column(Modifier.fillMaxWidth()) {
                Text("Dirección de Envío")
                Text(selectedAddress, fontWeight = FontWeight.Medium)
                TextButton(onClick = {
                    val intent = Intent(context, UserAddressesActivity::class.java)
                    addressLauncher.launch(intent)
                }) {
                    Text("Modificar")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { val intent = Intent(context, ConfirmationActivity::class.java)
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("PAGAR")
            }
        }
    }
}