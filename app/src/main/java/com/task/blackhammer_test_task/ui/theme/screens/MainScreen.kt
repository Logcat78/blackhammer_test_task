package com.task.blackhammer_test_task.ui.theme.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.task.blackhammer_test_task.services.GestureAccessibilityService
import com.task.blackhammer_test_task.viewmodel.GestureViewModel
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: GestureViewModel,
    onNavigate: () -> Unit,

){
    var buttonState = false
    val buttonText = remember { mutableStateOf("Начать") }
    val context = LocalContext.current
    viewModel.feelLiveData()
    val launch = viewModel.launchLiveData.observeAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                if(launch!!.checkEnableAccessibilityService(context)){
                    if(getIpText().isNotEmpty()){
                        val job = launch.getParams(getIpText())
                        if(!buttonState){
                            launch.openChrome(context)
                            buttonText.value  = "Пауза"
                            buttonState = true
                            GestureAccessibilityService.swipeState = true
                            job.start()

                        }else{
                            buttonState = false
                            buttonText.value  = "Начать"
                            GestureAccessibilityService.swipeState = false
                            job.cancel()
                        }
                    }else{
                        Toast.makeText(context, "Вы не ввели ip адрес и порт", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "Вы не включили сервис специальных возможностей", Toast.LENGTH_SHORT).show()
                }

            },
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = buttonText.value)
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
            Text(text = "Включить сервис специальных возможностей")
        }
    }
}