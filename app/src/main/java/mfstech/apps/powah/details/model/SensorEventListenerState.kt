package mfstech.apps.powah.details.model

import mfstech.apps.powah.common.sse.SensorEvent

sealed class SensorEventListenerState {
    data object Connecting : SensorEventListenerState()
    data class ConnectionFailed(val throwable: Throwable?): SensorEventListenerState()
    data class SensorEventListenerReceived(val sensorEvent: SensorEvent): SensorEventListenerState()
}