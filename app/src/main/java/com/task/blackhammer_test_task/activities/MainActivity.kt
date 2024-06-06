package com.task.blackhammer_test_task.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.task.blackhammer_test_task.ui.theme.Navigate
import com.task.blackhammer_test_task.ui.theme.screens.ConfigScreen
import com.task.blackhammer_test_task.ui.theme.screens.MainScreen
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigate()
        }
    }
}