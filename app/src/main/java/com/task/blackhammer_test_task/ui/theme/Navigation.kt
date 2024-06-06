package com.task.blackhammer_test_task.ui.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.blackhammer_test_task.ui.theme.screens.ConfigScreen
import com.task.blackhammer_test_task.ui.theme.screens.MainScreen
@Composable
fun Navigate() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {

        composable("main"){
            MainScreen() {
                navController.navigate("config")
            }

        }

        composable("config"){
            ConfigScreen()  {
                navController.navigate("main")
            }
        }

    }
}