package com.mfstech.powah.home.business

import com.mfstech.powah.common.database.DeviceDao
import com.mfstech.powah.common.database.model.Device
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val dao: DeviceDao
) : HomeRepository {

    override fun findDevices(search: String): Flow<List<Device>> = dao.find(search)
}