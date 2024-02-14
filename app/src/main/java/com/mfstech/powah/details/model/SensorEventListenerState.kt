package com.mfstech.powah.details.model

import com.mfstech.powah.common.business.sse.SensorEvent

sealed class SensorEventListenerState {
    data object Connecting : SensorEventListenerState()
    data class ConnectionFailed(val throwable: Throwable?): SensorEventListenerState()
    data class SensorEventListenerReceived(val sensorEvent: SensorEvent): SensorEventListenerState()
}