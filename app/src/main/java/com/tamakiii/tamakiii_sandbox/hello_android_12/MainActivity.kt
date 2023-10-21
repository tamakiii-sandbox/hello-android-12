package com.tamakiii.tamakiii_sandbox.hello_android_12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tamakiii.tamakiii_sandbox.hello_android_12.ui.theme.Helloandroid12Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Helloandroid12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val greetingText = remember { mutableStateOf("Loading...") }

    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.IO) {
            val process = Runtime.getRuntime().exec("/data/local/tmp/hello")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = reader.readLine()
            greetingText.value = "Hello $output!"
        }
    }

    Text(
        text = greetingText.value,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Helloandroid12Theme {
        Greeting()
    }
}
