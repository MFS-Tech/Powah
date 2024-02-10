package com.mfstech.powah.home.business

import com.mfstech.powah.common.database.DeviceDao
import com.mfstech.powah.common.database.model.Device

class HomeRepositoryImpl(
    private val dao: DeviceDao
) : HomeRepository {

    override suspend fun findDevices(search: String): List<Device> = dao.find(search)
}