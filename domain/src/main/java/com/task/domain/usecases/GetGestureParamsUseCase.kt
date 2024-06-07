package com.task.domain.usecases

import com.task.domain.entities.GestureParams
import com.task.domain.repositories.GestureRepository

class GetGestureParamsUseCase(
    private val gestureRepository: GestureRepository,
) {
    suspend operator fun invoke(url: String){
        gestureRepository.getGestureParams(url)
    }
}