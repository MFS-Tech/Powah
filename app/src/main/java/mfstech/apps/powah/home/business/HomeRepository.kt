package mfstech.apps.powah.home.business

import mfstech.apps.powah.common.database.model.Device

interface HomeRepository {

    suspend fun findDevices(search: String = ""): List<Device>
}