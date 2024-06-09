package com.task.blackhammer_test_task.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp



fun getIpText(): String{
    return ipText1
}
var ipText1: String = ""
@Composable
fun ConfigScreen(onNavigate: () -> Unit){
    val ipTextState = remember { mutableStateOf("")  }
    ipText1 = ipTextState.value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ){
        TextField(
            value = ipTextState.value,
            onValueChange = { newText ->
                ipTextState.value = newText
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {}),
            label = {Text("ip адрес и порт")},
            placeholder = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                ipText1  =  ipTextState.value
                Toast.makeText(context,  "Адрес и порт сохранены", Toast.LENGTH_LONG).show()
            },
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Подтвердить")
        }
        Button(
            onClick = {onNavigate()},
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Назад")
        }

        Text(
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Пример ввода ip адреса и порта: ws://127.0.0.1:8080",
            textAlign  =  TextAlign.Center,
        )

    }
}