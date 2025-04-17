package com.example.otraversion

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtraversionTheme {
                SplashScreen(
                    onIngresarClick = {
                        // Navegar a MainActivity2 al hacer clic
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        startActivity(intent)

                        // Opcional: cerrar la actividad actual si no quieres volver atrás
                        // finish()
                    }
                )
            }
        }
    }
}

@Composable
fun SplashScreen(onIngresarClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8AA63F)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "FOOD FAST",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Image(
                painter = painterResource(id = R.drawable.food_logo),
                contentDescription = "Logo de Food Fast",
                modifier = Modifier
                    .size(180.dp)
            )

            Button(
                onClick = onIngresarClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF696969),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
            ) {
                Text(text = "INGRESAR")
            }
        }
    }
}