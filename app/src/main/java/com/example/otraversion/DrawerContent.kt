package com.example.otraversion

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerContent(username: String) {
    val context = LocalContext.current

    ModalDrawerSheet(
        drawerContainerColor = Color(0xFF1E2B39),
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                tint = Color.LightGray
            )

            Text(
                text = username,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            DrawerOption("Inicio") {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
            DrawerOption("Mi Perfil") {
                context.startActivity(Intent(context, ProfileActivity::class.java))
            }
            DrawerOption("Departamentos") {
                val intent = Intent(context, FoodActivity::class.java)
                context.startActivity(intent)
            }

            Spacer(modifier = Modifier.weight(1f))

            DrawerOption("Facebook") {
                val intent = Intent(context, FacebookActivity::class.java)
                context.startActivity(intent)
            }
            DrawerOption("Configuración") {
                val intent = Intent(context, SettingsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun DrawerOption(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
