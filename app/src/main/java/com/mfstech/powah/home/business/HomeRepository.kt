package com.mfstech.powah.home.business

import com.mfstech.powah.common.database.model.Device

interface HomeRepository {

    suspend fun findDevices(search: String = ""): List<Device>
}