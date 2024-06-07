package com.task.blackhammer_test_task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.blackhammer_test_task.actions.Launch
import com.task.domain.usecases.GetGestureParamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class GestureViewModel @Inject constructor(
    private val launch: Launch,
    private val getGestureParamsUseCase: GetGestureParamsUseCase
): ViewModel() {
    val launchLiveData = MutableLiveData<Launch>()
    val getGestureParamsUseCaseLiveData = MutableLiveData<GetGestureParamsUseCase>()

    fun feelLiveData(){
        launchLiveData.value = launch
        getGestureParamsUseCaseLiveData.value  = getGestureParamsUseCase
    }

}