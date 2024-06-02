package com.task.blackhammer_test_task

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.blackhammer_test_task.ui.theme.Blackhammer_test_taskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.background
            ) {
                Column {
                    Button(modifier  = Modifier
                        ,onClick  = {
                            val gesture = GestureAccessibilityService()
                            gesture.swipe()
                            Log.d("gp","swipe")

                        }
                    ){
                        Text(text = "свайпать")
                    }




                    Button(modifier  = Modifier
                        .fillMaxHeight()
                        .padding(105.dp),onClick  = {
                        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))

                    }
                    ){
                        Text(text = "Запросить разрешение")
                    }


                }

            }
        }
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
    Blackhammer_test_taskTheme {
        Greeting("Android")
    }
}