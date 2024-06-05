package com.task.data.api

import android.util.Log
import com.task.domain.entities.GestureParams
import com.task.domain.repositories.GestureRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class GestureWebSocket: GestureRepository{

    override suspend fun getGestureParams(): GestureParams {
        var gestureParams: GestureParams? = null
        val client = OkHttpClient()
        val request = Request
            .Builder()
            .url("")
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

            }

        })
        client.dispatcher.executorService.shutdown()
        return gestureParams!!
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