package com.mfstech.powah.details.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.presenter.CommonViewModel
import com.mfstech.powah.details.business.DetailsRepository
import com.mfstech.powah.details.model.SensorEventListenerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailsViewModel(
    override val view: DetailsContract.View,
    private val id: Int,
    private val repository: DetailsRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), DetailsContract.ViewModel {

    private var job: Job? = null

    private fun handleNewEventState(state: SensorEventListenerState) {
        when (state) {
            is SensorEventListenerState.Connecting -> view.bindDeviceConnecting()
            is SensorEventListenerState.ConnectionFailed -> view.bindConnectionFailure(state.throwable)
            is SensorEventListenerState.SensorEventListenerReceived -> view.bindNewEvent(state.sensorEvent)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onResume() {
        job = viewModelScope.launch(dispatcher) {
            repository.getDevice(id)
                .filterNotNull()
                .onEach(view::bindDevice)
                .flowOn(Dispatchers.Main)
                .flatMapMerge { device ->
                    repository.getDeviceSensors(device)
                        .onEach(::handleNewEventState)
                        .flowOn(Dispatchers.Main)
                }.collect()
        }
    }

    override fun onEditClicked() {
        view.openEditDevice(id)
    }

    override fun onPause() {
        job?.cancel()
    }

    override fun onBackClicked() {
        view.goBack()
    }
}