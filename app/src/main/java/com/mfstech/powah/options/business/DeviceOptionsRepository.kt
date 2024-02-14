package com.mfstech.powah.options.business

import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

interface DeviceOptionsRepository {

    suspend fun delete(device: Device)
    suspend fun getDevice(id: Int): Flow<Device>
}