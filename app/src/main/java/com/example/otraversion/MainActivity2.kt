package com.example.otraversion

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otraversion.ui.theme.OtraversionTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtraversionTheme {
                val context = LocalContext.current

                LoginScreen(
                    onLoginClick = { username, password ->
                        if (username.isNotBlank() && password.isNotBlank()) {
                            val intent = Intent(context, HomeActivity::class.java)
                            intent.putExtra("username", username)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Por favor llena ambos campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onRegisterClick = {
                        val intent = Intent(context, RegisterActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FOOD FAST",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8AA63F),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario o Correo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8AA63F),
                contentColor = Color.White
            )
        ) {
            Text("Ingresar", fontSize = 16.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )
            Text(
                text = "O",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )
        }

        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Registrarte",
                color = Color(0xFF8AA63F),
                fontSize = 16.sp
            )
        }
    }
}