package mfstech.apps.powah.details.business

import kotlinx.coroutines.flow.Flow
import mfstech.apps.powah.common.database.model.Device
import mfstech.apps.powah.details.model.SensorEventListenerState

interface DetailsRepository {

    fun getDeviceSensors(): Flow<SensorEventListenerState>
    suspend fun deleteDevice(device: Device)
}