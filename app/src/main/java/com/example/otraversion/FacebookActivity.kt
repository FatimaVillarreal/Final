package com.example.otraversion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.otraversion.ui.theme.OtraversionTheme

class FacebookActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Intent to open the Facebook app or website
        val facebookIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"))
        facebookIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(facebookIntent)

        // Optionally finish this activity so it doesn't stay in back stack
        finish()
    }
}