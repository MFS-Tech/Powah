package com.mfstech.powah.details.presenter

import androidx.lifecycle.viewModelScope
import com.mfstech.powah.common.CommonViewModel
import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.details.business.DetailsRepository
import com.mfstech.powah.details.model.SensorEventListenerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailsViewModel(
    override val view: DetailsContract.View,
    private val device: Device,
    private val repository: DetailsRepository,
    private val dispatcher: CoroutineContext
) : CommonViewModel(), DetailsContract.ViewModel {

    private var job: Job? = null

    private fun handleNewEventState(state: SensorEventListenerState) {
        when (state) {
            is SensorEventListenerState.Connecting -> view.bindDeviceConnecting()
            is SensorEventListenerState.ConnectionFailed -> view.bindConnectionFailure()
            is SensorEventListenerState.SensorEventListenerReceived -> view.bindNewEvent(state.sensorEvent)
        }
    }

    override fun onResume() {
        view.bindDevice(device)
        job = viewModelScope.launch(dispatcher) {
            repository.getDeviceSensors()
                .onEach(::handleNewEventState)
                .flowOn(Dispatchers.Main)
                .collect()
        }
    }

    override fun onEditClicked() {
        view.openEditDevice(device)
    }

    override fun onDeleteClicked() {
        view.showDeleteConfirmationDialog()
    }

    override fun onDeleteConfirm() {
        viewModelScope.launch(dispatcher) { repository.deleteDevice(device) }
        view.goBack()
    }

    override fun onPause() {
        job?.cancel()
    }

    override fun onBackClicked() {
        view.goBack()
    }
}