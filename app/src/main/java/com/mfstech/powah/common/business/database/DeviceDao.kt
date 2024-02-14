package com.mfstech.powah.common.business.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mfstech.powah.common.business.database.model.Device
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Upsert
    suspend fun save(device: Device)

    @Delete
    suspend fun delete(device: Device): Int

    @Query("SELECT * FROM Device WHERE id = :id")
    fun get(id: Int): Flow<Device?>

    @Query("SELECT * FROM Device WHERE name LIKE '%'||:search||'%' OR route LIKE '%'||:search||'%'")
    fun find(search: String): Flow<List<Device>>
}