package mfstech.apps.powah.home.business

import mfstech.apps.powah.common.database.DeviceDao
import mfstech.apps.powah.common.database.model.Device

class HomeRepositoryImpl(
    private val dao: DeviceDao
) : HomeRepository {

    override suspend fun findDevices(search: String): List<Device> = dao.find(search)
}