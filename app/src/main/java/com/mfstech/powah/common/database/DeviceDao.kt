package com.mfstech.powah.common.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mfstech.powah.common.database.model.Device

@Dao
interface DeviceDao {
    @Upsert
    suspend fun save(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Query("SELECT * FROM Device WHERE id = :id")
    suspend fun get(id: Int): Device?

    @Query("SELECT * FROM Device WHERE name LIKE '%'||:search||'%' OR route LIKE '%'||:search||'%'")
    suspend fun find(search: String): List<Device>
}