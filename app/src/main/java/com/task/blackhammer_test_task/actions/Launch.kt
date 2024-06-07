package com.task.blackhammer_test_task.actions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.accessibility.AccessibilityManager
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

    fun checkUnableAccessibilityService(context: Context): Boolean {
        val accessibilityManager =
            context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        return accessibilityManager.isEnabled
    }
}