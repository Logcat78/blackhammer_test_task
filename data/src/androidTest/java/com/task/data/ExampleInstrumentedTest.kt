package com.task.data

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.task.data.test", appContext.packageName)
    }
    @Test
    fun testGetGestureParametersFromText(){
        val message = "moveToX(100), moveToY(200), lineToX(300), lineToY(400), duration(500)"

        val regex = Regex("moveToX\\((\\d+)\\), moveToY\\((\\d+)\\), lineToX\\((\\d+)\\), lineToY\\((\\d+)\\), duration\\((\\d+)\\)")

        val match = regex.find(message)

        if (match != null) {
            val moveToX = match.groupValues[1].toInt()
            val moveToY = match.groupValues[2].toInt()
            val lineToX = match.groupValues[3].toInt()
            val lineToY = match.groupValues[4].toInt()
            val duration = match.groupValues[5].toInt()
            Log.d("Test", "moveToX:$moveToX, moveToY:$moveToY, lineToX:$lineToX, lineToY:$lineToY, duration:$duration")

        }
    }
}