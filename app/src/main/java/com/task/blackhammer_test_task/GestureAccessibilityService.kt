package com.task.blackhammer_test_task

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class GestureAccessibilityService: AccessibilityService(){
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.d("gp", "Event")
    }

    fun swipe(){
        val path: Path = Path()
        path.moveTo(100f, 10f)
        path.lineTo(100f, 100f)

        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(
                path,
                100L,
                100L,
            ))


        Log.d("gp", "Event123")

        dispatchGesture(gesture.build(), object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                Log.d("gp","onCompleted")
            }

            override fun onCancelled(gestureDescription: GestureDescription?) {
                Log.d("gp","onCancelled")
            }
        }, null)
    }

    override fun onInterrupt() {
        Log.d("gp","onInterrupt")
    }

}