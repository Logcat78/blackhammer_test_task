package com.task.blackhammer_test_task.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {},
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Начать")
        }

        Button(
            onClick = {},
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Конфигурация")
        }

        Button(
            onClick = {},
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Запросить разрешение")
        }
    }
}