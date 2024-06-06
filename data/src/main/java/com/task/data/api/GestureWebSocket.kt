package com.task.data.api

import android.util.Log
import com.task.domain.entities.GestureParams
import com.task.domain.repositories.GestureRepository
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class GestureWebSocket: GestureRepository{
    companion object{
        var gestureParams: GestureParams = GestureParams(1f,2f,2f,3f, 1)
    }

    override suspend fun getGestureParams() {
        val client = OkHttpClient()
        val request = Request
            .Builder()
            .url("ws://a712-193-32-202-108.ngrok-free.app/ws")
            .build()

        val webSocket: WebSocket = client.newWebSocket(request, object : WebSocketListener(){
            override fun onOpen(webSocket: WebSocket, response: Response) {

            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                gestureParams = getGestureParamsFromText(text)!!
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {

            }
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {

            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.d("gp", response?.message.toString())
            }

        })
        client.dispatcher.executorService.shutdown()
        webSocket.send("getGestureParams")
        delay(3000)
    }

    override fun getGestureParamsFromText(text: String): GestureParams?  {
        val regex = Regex("moveToX\\((\\d+)\\), moveToY\\((\\d+)\\), lineToX\\((\\d+)\\), lineToY\\((\\d+)\\), duration\\((\\d+)\\)")
        val match = regex.find(text)

        if (match != null) {
            val moveToX = match.groupValues[1].toFloat()
            val moveToY = match.groupValues[2].toFloat()
            val lineToX = match.groupValues[3].toFloat()
            val lineToY = match.groupValues[4].toFloat()
            val duration = match.groupValues[5].toInt()
            return GestureParams(moveToX, moveToY, lineToX, lineToY, duration)

        }else
            return null
    }
}