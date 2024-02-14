package com.mfstech.powah.input.business

import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

interface InputRepository {
    fun getDevice(id: Int): Flow<Device?>
    suspend fun save(device: Device)
}