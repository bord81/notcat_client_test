package com.example.notcattest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notcattest.ui.theme.NotCatTestTheme
import com.notcat.NotCatClient

class MainActivity : ComponentActivity() {
    private var notcat_logger: NotCatClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notcat_logger = NotCatClient.Init()
        enableEdgeToEdge()
        setContent {
            NotCatTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "NotCat",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        notcat_logger?.Log("Hello from Kotlin example!") 
    }

    override fun onDestroy() {
        notcat_logger?.Close()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotCatTestTheme {
        Greeting("Android")
    }
}