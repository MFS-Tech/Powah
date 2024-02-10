package com.mfstech.powah.input.business

import com.mfstech.powah.common.database.model.Device
import kotlinx.coroutines.flow.Flow

interface InputRepository {
    suspend fun get(id: Int): Flow<Device>
    suspend fun save(device: Device)
    suspend fun delete(device: Device)
}