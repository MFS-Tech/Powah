package com.mfstech.powah.input.business

import com.mfstech.powah.common.business.database.DeviceDao
import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

class InputRepositoryImpl(
    private val dao: DeviceDao
) : InputRepository {
    override fun getDevice(id: Int): Flow<Device?> = dao.get(id)

    override suspend fun save(device: Device) = dao.save(device)
}