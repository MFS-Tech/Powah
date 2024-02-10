package com.mfstech.powah.details.business

import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.details.model.SensorEventListenerState
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getDeviceSensors(): Flow<SensorEventListenerState>
    suspend fun deleteDevice(device: Device)
}