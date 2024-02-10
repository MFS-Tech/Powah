package com.mfstech.powah.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mfstech.powah.common.database.model.Device

const val DB_NAME = "powah_database"
const val DB_VERSION = 1

@Database(entities = [Device::class], version = DB_VERSION)
abstract class PowahDatabase : RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao
}

fun getPowahDatabase(context: Context): PowahDatabase {
    return Room.databaseBuilder(
        context,
        PowahDatabase::class.java,
        DB_NAME
    ).build()
}