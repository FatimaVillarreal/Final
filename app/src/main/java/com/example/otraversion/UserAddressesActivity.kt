package com.example.otraversion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

class UserAddressesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                UserAddressesScreen(onAddressSelected = { selected ->
                    val resultIntent = Intent().apply {
                        putExtra("selectedAddress", selected)
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAddressesScreen(onAddressSelected: (String) -> Unit) {
    var selectedAddress by remember { mutableStateOf(0) }
    var addresses by remember {
        mutableStateOf(
            listOf(
                "Juan Perez\nSletters Lane[BR]10013 New York[BR]NY, USA",
                "Juan Perez\nFeathers Hooves Drive[BR]10011 New York[BR]NY, USA"
            )
        )
    }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newAddress = result.data?.getStringExtra("newAddress")
            if (!newAddress.isNullOrBlank()) {
                addresses = addresses + newAddress
            }
        }
    }

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            addresses.forEachIndexed { index, address ->
                AddressCard(
                    address = address,
                    isSelected = selectedAddress == index,
                    onSelect = {
                        selectedAddress = index
                        onAddressSelected(address)
                    },
                    onDelete = { /* TODO: Delete logic */ }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = {
                    val intent = Intent(context, AddAddressActivity::class.java)
                    launcher.launch(intent)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE09B)),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    "AÑADIR UNA DIRECCIÓN",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun AddressCard(address: String, isSelected: Boolean, onSelect: () -> Unit, onDelete: () -> Unit) {
    val color = if (isSelected) Color(0xFFEF9A9A) else Color(0xFFF5F5F5)

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .background(color)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                address.split("[BR]").forEach {
                    Text(it.trim(), fontSize = 14.sp)
                }
            }

            if (!isSelected) {
                IconButton(onClick = onDelete) {
                    Text("X", fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }
        }
    }
}
