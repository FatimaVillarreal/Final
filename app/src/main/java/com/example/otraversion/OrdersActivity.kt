package com.example.otraversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme

class OrdersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                OrdersScreen()
            }
        }
    }
}

data class ProductOrder(
    val imageRes: Int,
    val name: String,
    val date: String,
    val price: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen() {
    val compra1 = listOf(
        ProductOrder(R.drawable.jugo_jumex, "Jugo Jumex", "20/01/2025", "$30")
    )
    val compra2 = listOf(
        ProductOrder(R.drawable.nutella, "Nutella", "5/02/2025", "$130"),
        ProductOrder(R.drawable.chips_ahoy, "Galletas Chips", "5/02/2025", "$30"),
        ProductOrder(R.drawable.sopa_fideo, "Sopa de Fideo", "5/02/2025", "$15")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FOOD FAST") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = "Mis Pedidos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text("Compra 1", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 16.dp, top = 8.dp))
                }
                items(compra1) { item ->
                    OrderItemCard(item)
                }
                item {
                    Text("Compra 2", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 16.dp, top = 16.dp))
                }
                items(compra2) { item ->
                    OrderItemCard(item)
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(order: ProductOrder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = order.imageRes),
                contentDescription = order.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(order.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(order.date, fontSize = 12.sp, color = Color.Gray)
            }
            Text(order.price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}