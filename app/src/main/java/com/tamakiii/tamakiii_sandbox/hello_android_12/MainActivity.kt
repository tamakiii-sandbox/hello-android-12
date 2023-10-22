package com.tamakiii.tamakiii_sandbox.hello_android_12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tamakiii.tamakiii_sandbox.hello_android_12.ui.theme.Helloandroid12Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        copyBinaryToExecutableLocation()
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

    private fun copyBinaryToExecutableLocation() {
        val assetManager = assets
        val inputStream = assetManager.open("hello")  // Assume binary is named "hello"
        val outFile = File(filesDir, "hello")
        val outputStream = FileOutputStream(outFile)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.flush()
        outputStream.close()
        outFile.setExecutable(true)
    }

    @Composable
    fun Greeting(modifier: Modifier = Modifier) {
        var greetingText by remember { mutableStateOf("Loading...") }

        LaunchedEffect(key1 = Unit) {
            withContext(Dispatchers.IO) {
                try {
                    val process = Runtime.getRuntime().exec("${filesDir}/hello")
                    val reader = BufferedReader(InputStreamReader(process.inputStream))
                    val output = reader.readLine()
                    greetingText = "# $output"
                } catch (e: Exception) {
                    greetingText = e.message ?: "An error occurred"
                }
            }
        }

        Text(
            text = greetingText,
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
}
