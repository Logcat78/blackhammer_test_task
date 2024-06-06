package com.task.blackhammer_test_task.ui.theme.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.task.blackhammer_test_task.services.GestureAccessibilityService
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(onNavigate: () -> Unit){
    var buttonState = false
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                val job = CoroutineScope(Dispatchers.IO).launch{
                    val getGestureParamsUseCase = GetGestureParamsUseCase(GestureWebSocket())
                    getGestureParamsUseCase.invoke()
                }
                if(!buttonState){
                    val intentChromeIntent  = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                    context.startActivity(intentChromeIntent)
                    buttonState = true
                    GestureAccessibilityService.swipeState = true
                    job.start()

                }else{
                    buttonState = false
                    GestureAccessibilityService.swipeState = false
                    job.cancel()
                }

            },
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Начать")
        }

        Button(
            onClick = {onNavigate()},
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Конфигурация")
        }

        Button(
            onClick = {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                context.startActivity(intent)
            },
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Запросить разрешение")
        }
    }
}