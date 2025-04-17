package com.example.otraversion

import android.content.Intent
import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productList = intent.getSerializableExtra("cart_items") as? ArrayList<CartItem> ?: arrayListOf()
        setContent {
            OtraversionTheme {
                CartScreen(productList)
            }
        }
    }
}

data class CartItem(val imageRes: Int, val name: String, val price: Double) : java.io.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartItems: List<CartItem>) {
    val context = LocalContext.current
    val totalProductos = cartItems.size
    val totalPrecio = cartItems.sumOf { it.price }
    val costoEnvio = "$N"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FOOD FAST") },
                navigationIcon = {
                    IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            cartItems.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.name, fontWeight = FontWeight.Bold)
                        Text(text = "Cantidad: 1")
                    }
                    Text(text = "$${"%.2f".format(item.price)}")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Total de productos: $totalProductos", fontSize = 16.sp)
            Text("Costo de envío: $costoEnvio", fontSize = 16.sp)
            Text("Total a pagar: $${"%.2f".format(totalPrecio)}", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { val intent = Intent(context, CheckoutActivity::class.java)
                    intent.putExtra("total", totalPrecio)
                    intent.putExtra("count", totalProductos)
                    intent.putExtra("username", "Juan Perez") // Puedes enviar el nombre real
                    context.startActivity(intent)},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("PAGAR")
            }
        }
    }
}