package com.task.blackhammer_test_task.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.task.blackhammer_test_task.ui.theme.Navigate
import com.task.blackhammer_test_task.ui.theme.screens.ConfigScreen
import com.task.blackhammer_test_task.ui.theme.screens.MainScreen
import com.task.blackhammer_test_task.viewmodel.GestureViewModel
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: GestureViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigate(viewModel)
        }
    }
}