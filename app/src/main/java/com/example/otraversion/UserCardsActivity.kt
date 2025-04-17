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

class UserCardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                UserCardsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCardsScreen() {
    var selectedCard by remember { mutableStateOf(0) }
    var cards by remember {
        mutableStateOf(
            listOf(
                "Visa\n**** **** **** 4242\n12/2034",
                "Visa\n**** **** **** 4242\n12/2034"
            )
        )
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newCard = result.data?.getStringExtra("newCard")
            if (!newCard.isNullOrBlank()) {
                cards = cards + newCard
                selectedCard = cards.lastIndex
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

            cards.forEachIndexed { index, card ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .background(if (selectedCard == index) Color(0xFFEF9A9A) else Color(0xFFF5F5F5))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedCard == index,
                            onClick = { selectedCard = index },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            card.split("\n").forEach {
                                Text(it.trim(), fontSize = 14.sp)
                            }
                        }

                        if (selectedCard != index) {
                            IconButton(onClick = { /* TODO: Delete card logic */ }) {
                                Text("X", fontWeight = FontWeight.Bold, color = Color.Gray)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = {
                    val intent = Intent(context, AddCardActivity::class.java)
                    launcher.launch(intent)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCE09B)),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("AÑADIR UNA TARJETA", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
