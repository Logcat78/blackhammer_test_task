package com.task.blackhammer_test_task.actions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class Launch @Inject constructor(
    private val getGestureParamsUseCase: GetGestureParamsUseCase
){
    fun openChrome(context: Context) {
        val intentChromeIntent  = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        context.startActivity(intentChromeIntent)
    }

    fun getParams(url: String): Job{
        val job = CoroutineScope(Dispatchers.IO).launch{
            getGestureParamsUseCase.invoke(url)
        }
        return job
    }

    fun checkEnableAccessibilityService(context: Context): Boolean {
        val accessibilityManager =
            context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        return accessibilityManager.isEnabled
    }

    fun enableSettingsAccessibility(context: Context){
        Toast.makeText(context,  "Нажмите на 3 точки в углу экрана и включитите настройки, возможно на вашем устройстве это будет по другому", Toast.LENGTH_LONG).show()
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", "com.task.blackhammer_test_task", null)
        intent.data = uri
        context.startActivity(intent)
    }
}