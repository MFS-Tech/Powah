package com.mfstech.powah.input.business

import com.mfstech.powah.common.database.DeviceDao
import com.mfstech.powah.common.database.model.Device
import kotlinx.coroutines.flow.Flow

class InputRepositoryImpl(
    private val dao: DeviceDao
) : InputRepository {
    override suspend fun get(id: Int): Flow<Device?> = dao.get(id)

    override suspend fun save(device: Device) = dao.save(device)
}