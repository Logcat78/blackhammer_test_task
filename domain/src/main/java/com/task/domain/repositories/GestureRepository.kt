package com.task.domain.repositories

import com.task.domain.entities.GestureParams

interface GestureRepository {
    suspend fun getGestureParams(): GestureParams
    fun getGestureParamsFromText(text: String): GestureParams?

}