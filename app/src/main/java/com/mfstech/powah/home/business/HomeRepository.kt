package com.mfstech.powah.home.business

import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun findDevices(search: String = ""): Flow<List<Device>>
    suspend fun deleteDevice(device: Device): Boolean
}