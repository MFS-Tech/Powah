package mfstech.apps.powah.input.business

import mfstech.apps.powah.common.database.DeviceDao
import mfstech.apps.powah.common.database.model.Device

class InputRepositoryImpl(
    private val dao: DeviceDao
) : InputRepository {
    override suspend fun get(id: Int): Device? = dao.get(id)

    override suspend fun save(device: Device) = dao.save(device)

    override suspend fun delete(device: Device) = dao.delete(device)
}