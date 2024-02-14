package com.mfstech.powah.home.business

import com.mfstech.powah.common.business.database.DeviceDao
import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val dao: DeviceDao
) : HomeRepository {

    override fun findDevices(search: String): Flow<List<Device>> = dao.find(search)

    override suspend fun deleteDevice(device: Device): Boolean {
        return dao.delete(device) > 0
    }
}