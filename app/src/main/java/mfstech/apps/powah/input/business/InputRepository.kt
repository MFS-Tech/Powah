package mfstech.apps.powah.input.business

import mfstech.apps.powah.common.database.model.Device

interface InputRepository {
    suspend fun get(id: Int): Device?
    suspend fun save(device: Device)
    suspend fun delete(device: Device)
}