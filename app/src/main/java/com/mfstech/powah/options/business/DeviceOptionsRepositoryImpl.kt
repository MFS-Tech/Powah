package com.mfstech.powah.options.business

import com.mfstech.powah.common.business.database.DeviceDao
import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class DeviceOptionsRepositoryImpl(
    private val dao: DeviceDao
) : DeviceOptionsRepository {

    override suspend fun getDevice(id: Int): Flow<Device> = dao.get(id).filterNotNull()

    override suspend fun delete(device: Device) {
        dao.delete(device)
    }
}