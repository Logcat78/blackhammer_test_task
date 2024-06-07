package com.task.blackhammer_test_task.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.task.data.api.GestureWebSocket
import com.task.domain.entities.GestureParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GestureAccessibilityService: AccessibilityService(){

    companion object{
        var swipeState: Boolean = false
    }
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.d("Gesture", "Event")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                delay(3000)
                if (swipeState){
                    swipe(GestureWebSocket.gestureParams!!)
                }
            }
        }

    }

    override fun onMotionEvent(event: MotionEvent) {
        super.onMotionEvent(event)
        Log.d("Gesture",  "MotionEvent")
    }
    fun swipe(
        gestureParams: GestureParams
    ){
        val path: Path = Path()
        path.moveTo(gestureParams.moveToX, gestureParams.moveToY)
        path.lineTo(gestureParams.lineToX, gestureParams.lineToY)

        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(
                path,
                0,
                gestureParams.duration.toLong(),
            ))


       dispatchGesture(gesture.build(), object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                Log.d("Gesture","onCompleted")
            }

            override fun onCancelled(gestureDescription: GestureDescription?) {
                Log.d("Gesture","onCancelled")
            }
        }, null)

    }

    override fun onInterrupt() {
        Log.d("Gesture","onInterrupt")
    }

}