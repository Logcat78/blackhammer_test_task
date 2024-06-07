package com.task.domain.repositories

import com.task.domain.entities.GestureParams

interface GestureRepository {
    suspend fun getGestureParams(url: String)
    fun getGestureParamsFromText(text: String): GestureParams?

}