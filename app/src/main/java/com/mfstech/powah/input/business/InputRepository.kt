package com.mfstech.powah.input.business

import com.mfstech.powah.common.database.model.Device

interface InputRepository {
    suspend fun get(id: Int): Device?
    suspend fun save(device: Device)
    suspend fun delete(device: Device)
}