package com.task.blackhammer_test_task.di

import com.task.blackhammer_test_task.actions.Launch
import com.task.blackhammer_test_task.ui.theme.screens.getIpText
import com.task.data.api.GestureWebSocket
import com.task.domain.usecases.GetGestureParamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GestureModule {
    @Provides
    fun provideLaunch(): Launch{
        return Launch(provideGetGestureParamsUseCase())
    }
    @Provides
    fun provideGetGestureParamsUseCase(): GetGestureParamsUseCase{
        return GetGestureParamsUseCase(GestureWebSocket())
    }
}