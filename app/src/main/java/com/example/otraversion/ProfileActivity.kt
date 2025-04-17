package com.example.otraversion

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra("username") ?: "Invitado"

        setContent {
            var updatedUsername by remember { mutableStateOf(username) }

            val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val newName = result.data?.getStringExtra("updatedName")
                    if (!newName.isNullOrBlank()) {
                        updatedUsername = newName
                        Toast.makeText(this, "Nombre actualizado", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            OtraversionTheme {
                ProfileDrawer(updatedUsername, launcher)
            }
        }
    }
}

@Composable
fun ProfileDrawer(username: String, launcher: ActivityResultLauncher<Intent>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(username)
        }
    ) {
        ProfileScreen(
            username = username,
            launcher = launcher,
            onMenuClick = { scope.launch { drawerState.open() } }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(username: String, launcher: ActivityResultLauncher<Intent>, onMenuClick: () -> Unit) {
    val context = LocalContext.current

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                tint = Color.LightGray
            )
            Text("¡Hola!", fontSize = 20.sp, color = Color.Gray)
            Text(username, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            ProfileOption(Icons.Outlined.List, "Mis Pedidos") {
                val intent = Intent(context, OrdersActivity::class.java)
                context.startActivity(intent)
            }

            ProfileOption(Icons.Outlined.Person, "Mis Detalles") {
                val intent = Intent(context, UserDetailsActivity::class.java)
                intent.putExtra("username", username)
                launcher.launch(intent)
            }

            ProfileOption(Icons.Outlined.Place, "Mis Direcciones") {
                val intent = Intent(context, UserAddressesActivity::class.java)
                context.startActivity(intent)
            }

            ProfileOption(Icons.Outlined.Info, "Mis Tarjetas") {
                val intent = Intent(context, UserCardsActivity::class.java)
                context.startActivity(intent)
            }

            ProfileOption(Icons.Outlined.ExitToApp, "Cerrar Sesión", Color.Red) {
                val intent = Intent(context, MainActivity2::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    text: String,
    color: Color = Color.DarkGray,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, tint = color, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, color = color)
    }
}