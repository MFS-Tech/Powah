package com.mfstech.powah.details.business

import com.mfstech.powah.common.database.model.Device
import com.mfstech.powah.details.model.SensorEventListenerState
import kotlinx.coroutines.flow.Flow
import okhttp3.Request

interface DetailsRepository {
    fun getDevice(id: Int): Flow<Device?>
    fun getDeviceSensors(device: Device): Flow<SensorEventListenerState>
    fun getRequest(device: Device): Request
}