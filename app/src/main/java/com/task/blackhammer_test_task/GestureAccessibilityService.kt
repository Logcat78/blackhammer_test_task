package com.task.blackhammer_test_task

import android.accessibilityservice.AccessibilityGestureEvent
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.accessibility.AccessibilityEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GestureAccessibilityService: AccessibilityService(){

    companion object{
        var swipeState: Boolean = false
    }
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.d("gp", "Event")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                delay(3000)
                if (swipeState){
                    swipe()
                }
            }
        }

    }

    override fun onMotionEvent(event: MotionEvent) {
        super.onMotionEvent(event)
        Log.d("gp",  "MotionEvent")
    }
    fun swipe(

    ){
        val path: Path = Path()
        path.moveTo(500f, 500f)
        path.lineTo(100f, 500f)

        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(
                path,
                0,
                500,
            ))


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